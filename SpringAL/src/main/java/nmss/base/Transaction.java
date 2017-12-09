package nmss.base;

import java.util.concurrent.BlockingQueue;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;

import nmss.Main.Start;
import nmss.util.AppConfig;
import nmss.util.Utility;

public abstract class Transaction implements Runnable {

	@Autowired
	@Resource(name = "transactionQueue")
	protected BlockingQueue<Transaction> transactionQueue = null;

	@Autowired
	@Resource(name = "requestQueue")
	protected BlockingQueue<Request> requestQueue = null;

	@Autowired
	protected Logger lFile = null;

	@Autowired
	protected AppConfig appConfig;

	@Autowired
	@Resource(name = "jdbcCrbtRequestDAOImpl")
	protected RequestDAO requestDAO;

	@Autowired
	protected Utility utility;

	protected Request request;

	public abstract boolean doTransaction(Request request);

	public void run() {
		try {
			String txnName = request.getTxnName().split(",")[0];
			// if end flow than status must not be clear of previous Transaction status
			if (!"End".equalsIgnoreCase(txnName)) {
				resetTxnStatus();
			}

			/* doTransaction method Must be override in child class */
			if(doTransaction(request)) {
				if (!"End".equalsIgnoreCase(txnName)) {

					Service service = Start.ctx.getBean(request.getFlowName().toLowerCase(), Service.class);

					Transaction txn = service.getNextState(request);
					if (txn == null) {
						lFile.info("Next Transaction not Found " + request);
					} else {
						txn.setRequest(request);
						request.txnStartTime(); // setting Start of Transaction
												// Time
												// for Total Time Taken
						transactionQueue.put(txn);
					}
				}
			}
		} catch (NoSuchBeanDefinitionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public void resetTxnStatus() {
		request.setTxnStatus("initial");
		request.setTxnStatusDesc("initial");
	}

}

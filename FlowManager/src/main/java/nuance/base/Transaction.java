package nuance.base;

import java.util.concurrent.BlockingQueue;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;

import nuance.base.pojo.Request;
import nuance.repository.RequestRepository;
import nuance.util.ApplicationContextProvider;
import nuance.util.Utility;

public abstract class Transaction implements Runnable {

	@Autowired
	@Resource(name = "transactionQueue")
	protected BlockingQueue<Transaction> transactionQueue = null;

	protected final Logger logger = LogManager.getLogger(Transaction.class);

	@Autowired
	@Resource(name = "requestQueue")
	protected BlockingQueue<Request> requestQueue = null;

	@Autowired
	protected RequestRepository requestRepository;

	@Autowired
	protected AppConfig appConfig;

	@Autowired
	protected Utility utility;

	protected Request request;

	public abstract boolean doTransaction(Request request);

	public void run() {
		try {
			String txnName = request.getTxnName().split(",")[0];
			// if end flow than status must not be clear of previous Transaction
			// status
			boolean isEndRequest = txnName.contains("end");
			if (!isEndRequest) {
				resetTxnStatus();
			}

			/* doTransaction method Must be override in child class */
			boolean result = doTransaction(request);

			if (isEndRequest)
				requestRepository.deleteByTid(request.getTid());
			else
				requestRepository.updateState(request);

			if (result) {
				if (!isEndRequest) {

					Service service = ApplicationContextProvider.getBean(request.getFlowName().toLowerCase(),
							Service.class);

					Transaction txn = service.getNextState(request);
					if (txn == null) {
						logger.info("Next Transaction not Found " + request);
					} else {
						logger.debug("Next Transaction Found " + request);
						txn.setRequest(request);
						request.txnStartTime(); // setting Start of Transaction
												// Time
												// for Total Time Taken
						transactionQueue.put(txn);
						logger.debug("Put into Transaction Queue " + request);
					}
				}
			}
		} catch (NoSuchBeanDefinitionException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e);
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

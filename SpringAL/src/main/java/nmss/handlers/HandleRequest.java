package nmss.handlers;

import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nmss.Main.Start;
import nmss.base.Request;
import nmss.base.Service;
import nmss.base.Transaction;
import nmss.util.JdbcCrbtRequestDAOImpl;

@Component("handleRequest")
public class HandleRequest implements Runnable {

	@Autowired
	@Resource(name="requestQueue")
	private LinkedBlockingQueue<Request> requestQueue = null;

	@Autowired
	private JdbcCrbtRequestDAOImpl jdbcRequestDAOImpl;
	
	@Autowired
	@Resource(name="transactionQueue")
	private LinkedBlockingQueue<Transaction> transactionQueue = null;

	@Autowired
	private Logger lFile = null;

	public void run() {
		while (true) {
			Request request = null;
			try {
				request = requestQueue.take();
				
				Service service = Start.ctx.getBean(request.getFlowName().toLowerCase(),Service.class);
				
				Transaction txn = service.getNextState(request);

				if (txn == null) {
					lFile.info("Transaction Cannot be null " + request);
				} else {
					txn.setRequest(request);
					request.startTime(); // setting Start of Request Time for Total Time Taken
					request.txnStartTime(); // setting Start of Transaction Time for Total Time Taken
					transactionQueue.put(txn);
					lFile.info("RequestQueue Size::"+requestQueue.size()+", TransactionQueueSize::"+transactionQueue.size()+", "+request);
				}
			}catch (NoSuchBeanDefinitionException e) {
				jdbcRequestDAOImpl.delete(request);
				lFile.info("No Flow Definition found "+request);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

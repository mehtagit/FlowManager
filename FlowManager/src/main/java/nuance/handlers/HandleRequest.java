package nuance.handlers;

import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;

import nuance.base.Service;
import nuance.base.Transaction;
import nuance.base.pojo.Request;
import nuance.repository.RequestRepository;
import nuance.util.ApplicationContextProvider;

@org.springframework.stereotype.Service
public class HandleRequest implements Runnable {

	@Autowired
	@Resource(name = "requestQueue")
	private LinkedBlockingQueue<Request> requestQueue = null;

	@Autowired
	@Resource(name = "transactionQueue")
	private LinkedBlockingQueue<Transaction> transactionQueue = null;

	private final Logger logger = LogManager.getLogger(HandleRequest.class);

	@Autowired
	private RequestRepository requestRepository;
	
	public void run() {
		while (true) {
			Request request = null;
			try {
				request = requestQueue.take();

				Service service = ApplicationContextProvider.getBean(request.getFlowName().toLowerCase(),
						Service.class);

				Transaction txn = service.getNextState(request);

				if (txn == null) {
					logger.info("Transaction Cannot be null " + request);
				} else {
					txn.setRequest(request);
					request.startTime(); // setting Start of Request Time for
											// Total Time Taken
					request.txnStartTime(); // setting Start of Transaction Time
											// for Total Time Taken
					transactionQueue.put(txn);
					logger.info("RequestQueue Size::" + requestQueue.size() + ", TransactionQueueSize::"
							+ transactionQueue.size() + ", " + request);
				}
			} catch (NoSuchBeanDefinitionException e) {
				/****/
				requestRepository.delete(request);
				
				logger.info("No Flow Definition found " + request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

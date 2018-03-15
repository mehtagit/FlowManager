package nuance.handlers;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import nuance.base.AppConfig;
import nuance.base.Transaction;

@Service
public class HandleTransaction implements Runnable {

	@Autowired
	@Resource(name = "transactionQueue")
	private LinkedBlockingQueue<Transaction> transactionQueue = null;

	protected final Logger logger = LogManager.getLogger(HandleTransaction.class);

	public ThreadPoolExecutor executor = null;

	@Autowired
	private AppConfig appConfig;

	@PostConstruct
	public void init() {
		this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(appConfig.getServiceThreadPoolSize());
	}

	public void run() {
		int tps = appConfig.getTransactionTps() / 1000;
		while (true) {
			try {
				Transaction txn = transactionQueue.take();
				logger.debug(txn.getClass().getName() + " Going to assign to thread pool " + txn.getRequest());
				executor.execute(txn);
				logger.debug(txn.getClass().getName() + " assigned to thread pool " + txn.getRequest());
				Thread.sleep(tps);
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
		}
	}

}

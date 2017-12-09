package nmss.handlers;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nmss.base.Transaction;
import nmss.util.AppConfig;

@Component("handleTransaction")
public class HandleTransaction implements Runnable {

	@Autowired
	@Resource(name = "transactionQueue")
	private LinkedBlockingQueue<Transaction> transactionQueue = null;

	@Autowired
	private Logger lFile = null;

	@Autowired
	private AppConfig appConfig;

	public ThreadPoolExecutor executor = null;

	@PostConstruct
	public void init() {
		this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(appConfig.serviceThreadPoolSize);
	}

	public void run() {
		while (true) {
			try {
				Transaction txn = transactionQueue.take();
				executor.execute(txn);
				Thread.sleep(appConfig.transactionTps / 1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

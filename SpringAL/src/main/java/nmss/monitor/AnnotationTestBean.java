package nmss.monitor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import nmss.base.Request;
import nmss.base.Transaction;
import nmss.handlers.HandleTransaction;
import nmss.util.AppConfig;

//@Component("myManagedBean")
//@ManagedResource(objectName = "Virgin:ABC=Fulfillment", description = "Virgin Fulfillment")
public class AnnotationTestBean {

	@Autowired
	private HandleTransaction handleTransaction;

	@Autowired
	private Logger lFile = null;

	@Autowired
	@Resource(name = "requestQueue")
	private LinkedBlockingQueue<Request> requestQueue = null;

	@Autowired
	@Resource(name = "transactionQueue")
	private LinkedBlockingQueue<Transaction> transactionQueue = null;

	@Autowired
	private AppConfig appConfig;

	@ManagedAttribute(description = "appConfig.isPullingFromDB")
	public boolean isPullingFromDB() {
		return appConfig.isPullingFromDB;
	}

	@ManagedAttribute(description = "Stop Pulling from DB for Fullfilment")
	public void LoadStop() {
		lFile.info("Going To Stop Pulling Data from DB for Fulfillment");
		appConfig.isPullingFromDB = false;
	}

	@ManagedAttribute(description = "Start Pulling from DB")
	public void LoadStart() {
		lFile.info("Going To Start Pulling Data from DB for Fulfillment");
		appConfig.isPullingFromDB = true;
	}

	/*
	 * @ManagedOperation(description =
	 * "Check permissions for the given activity")
	 * 
	 * @ManagedOperationParameters({
	 * 
	 * @ManagedOperationParameter(name = "activity", description =
	 * "The activity to check") })
	 */
	@ManagedAttribute(description = "Start Pulling from DB")
	public String getThreadPoolDetail() {
		ThreadPoolExecutor executor = handleTransaction.executor;
		String log = "Current Active Threads Count::" + executor.getActiveCount() + ", Pending Tasks in ThreadPoolQueue::"
				+ executor.getQueue().size()+", RequestQueueSize::+"+requestQueue.size()+", TransacQueueSize::"+transactionQueue.size()+", ";
		lFile.info(log);
		return log;
	}
}
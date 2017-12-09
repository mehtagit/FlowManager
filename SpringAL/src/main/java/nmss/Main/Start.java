package nmss.Main;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import nmss.handlers.AckHandler;
import nmss.handlers.HandleRequest;
import nmss.handlers.HandleTransaction;
import nmss.handlers.RetryResponse;
import nmss.handlers.TransQueuePicker;
import nmss.pojos.crbt.PackDetails;
import nmss.util.AppConfig;
import nmss.util.JdbcCrbtRequestDAOImpl;
import nmss.util.UdpServer;

@Component("start")
public class Start {

	public static ApplicationContext ctx;
	
	@Autowired
	private AppConfig config;

	public static void main(String[] args) {
		ctx = new ClassPathXmlApplicationContext("springObjectInjection.xml");
		// ApplicationContext ctx = new
		// FileSystemXmlApplicationContext("springObjectInjection.xml");
		ConcurrentHashMap<String, List<PackDetails>> mapOfFallBack = ctx.getBean("mapOfFallBack",
				ConcurrentHashMap.class);
		JdbcCrbtRequestDAOImpl jdbcRequestDAOImpl = ctx.getBean("jdbcCrbtRequestDAOImpl", JdbcCrbtRequestDAOImpl.class);
		
		for (PackDetails packDetails : jdbcRequestDAOImpl.getAllPacks()) 
		{
			if (mapOfFallBack.get(packDetails.getId()) == null) {
				ArrayList<PackDetails> list = new ArrayList<>();
				list.add(packDetails);
				mapOfFallBack.put(packDetails.getId(), list);
			} else {
				mapOfFallBack.get(packDetails.getId()).add(packDetails);
			}
		}
		Start obj = ctx.getBean("start",Start.class);
		obj.startThread();
	}

	public void startThread()
	{
		try {
			HandleRequest handleRequest = ctx.getBean("handleRequest", HandleRequest.class);
			new Thread(handleRequest, "HandleRequest").start();

			HandleTransaction handleTransaction = ctx.getBean("handleTransaction", HandleTransaction.class);
			new Thread(handleTransaction, "HandleTransaction").start();

			TransQueuePicker transQueuePicker = ctx.getBean("transQueuePicker", TransQueuePicker.class);
			new Thread(transQueuePicker, "TransQueuePicker").start();
			
			UdpServer udpServer = ctx.getBean("udpServer",UdpServer.class);
			udpServer.start();
			
			AckHandler ackHandler = ctx.getBean("ackHandler",AckHandler.class);
			ackHandler.start();
			
			if(config.retryAck)
			{
				RetryResponse retryThread = ctx.getBean("responseRerty",RetryResponse.class);
				retryThread.start();
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

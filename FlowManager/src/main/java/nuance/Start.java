package nuance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import nuance.handlers.HandleRequest;
import nuance.handlers.HandleTransaction;
import nuance.handlers.TransQueuePicker;
import nuance.nmss.AckHandler;
import nuance.nmss.RetryResponse;
import nuance.nmss.pojos.crbt.PackDetails;
import nuance.nmss.util.CrbtConfig;
import nuance.nmss.util.UdpServer;
import nuance.repository.RequestRepository;
import nuance.util.ApplicationContextProvider;

@ComponentScan(basePackages = "nuance")
@SpringBootApplication
public class Start {

	public static void main(String[] args) {
		SpringApplication.run(Start.class, args);

		HandleRequest handleRequest = ApplicationContextProvider.getBean("handleRequest", HandleRequest.class);
		new Thread(handleRequest, "HandleRequest").start();

		HandleTransaction handleTransaction = ApplicationContextProvider.getBean("handleTransaction",
				HandleTransaction.class);
		new Thread(handleTransaction, "HandleTransaction").start();

		TransQueuePicker transQueuePicker = ApplicationContextProvider.getBean("transQueuePicker",
				TransQueuePicker.class);
		new Thread(transQueuePicker, "TransQueuePicker").start();
		
		/*Crbt Code*/
		ConcurrentHashMap<String, List<PackDetails>> mapOfFallBack = ApplicationContextProvider.getBean("mapOfFallBack",
				ConcurrentHashMap.class);
		RequestRepository requestRepository = ApplicationContextProvider.context.getBean(RequestRepository.class);
		for (PackDetails packDetails : requestRepository.getAllPacks()) 
		{
			if (mapOfFallBack.get(packDetails.getId()) == null) {
				ArrayList<PackDetails> list = new ArrayList<>();
				list.add(packDetails);
				mapOfFallBack.put(packDetails.getId(), list);
			} else {
				mapOfFallBack.get(packDetails.getId()).add(packDetails);
			}
		}
		
		UdpServer udpServer = ApplicationContextProvider.getBean("udpServer",UdpServer.class);
		udpServer.start();
		
		AckHandler ackHandler = ApplicationContextProvider.getBean("ackHandler",AckHandler.class);
		ackHandler.start();
		
		if(ApplicationContextProvider.context.getBean(CrbtConfig.class).isRetryAck())
		{
			RetryResponse retryThread = ApplicationContextProvider.getBean("responseRerty",RetryResponse.class);
			retryThread.start();
		}
	}

}

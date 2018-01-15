package nuance.nmss;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nuance.base.AppConfig;
import nuance.base.pojo.Request;
import nuance.nmss.pojos.crbt.CrbtRequest;
import nuance.nmss.util.CrbtConfig;
import nuance.nmss.util.CrbtUtillity;
import nuance.repository.RequestRepository;

@Component("responseRerty")
public class RetryResponse extends Thread {

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private CrbtUtillity crbtUtill;

	@Autowired
	private CrbtConfig crbtConfig;

	@Autowired
	private Logger lFile = null;

	public void run() {
		lFile.info("retry of reponse thread is up");
		while (true) {
			try {
				List<Request> requestList = requestRepository.getPendingAcks();
				for (Request request : requestList) {
					sleep(1000);
					String response = crbtUtill.getResponseBack((CrbtRequest) request);
					crbtUtill.UDP_SEND(crbtConfig.getResponseIp(), crbtConfig.getResponsePort(), response, true);
				}
				sleep(1000 * 60);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

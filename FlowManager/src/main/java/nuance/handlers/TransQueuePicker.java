package nuance.handlers;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nuance.base.AppConfig;
import nuance.base.pojo.Request;
import nuance.nmss.pojos.crbt.CrbtRequest;
import nuance.nmss.util.CrbtConfig;
import nuance.nmss.util.CrbtUtillity;
import nuance.repository.RequestRepository;
import nuance.util.Validation;

@Service
public class TransQueuePicker implements Runnable {

	@Resource(name = "requestQueue")
	@Autowired
	private LinkedBlockingQueue<Request> requestQueue = null;

	protected final Logger logger = LogManager.getLogger(TransQueuePicker.class);

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	protected CrbtConfig crbtConfig;
	
	@Autowired
	private CrbtUtillity utility;
	
	@Autowired
	private Validation validation;

	public void run() {
		try {
			while (true) {
				while (appConfig.isPullingFromDb()) {
					List<Request> requestList = requestRepository.findAll();
					// requestRepository.findAll().forEach(requestList::add);
					if (requestList == null) {
						logger.info("Total Request Selected for fullfilment 0");
					} else {
						for (Request request : requestList) {
							if (request != null) {
								CrbtRequest tempreq = (CrbtRequest)request;
								String ackResponse = tempreq.getMsg();
								ackResponse = ackResponse.replaceAll("REQ", "ACK");
								ackResponse = "00000#5667799#CRBT#"+tempreq.getMsisdn()+"#"+ackResponse;
								utility.UDP_SEND(crbtConfig.getResponseIp(), crbtConfig.getResponsePort(), ackResponse, true);
								//tempreq.setMsg("");
								if (("activation").equalsIgnoreCase(request.getFlowName())) {
									if (validation.validateActivation(request)) {
										requestRepository.updateStatus(request);
										requestQueue.put(request);
									} else {
										String response = utility.getResponseBack((CrbtRequest) request);
										utility.UDP_SEND(crbtConfig.getResponseIp(), crbtConfig.getProvisningPort(), response, true);
										requestRepository.delete(request);
									}

								} else if ("renewal".equalsIgnoreCase(request.getFlowName())) {
									if (validation.validateRenewal(request)) {
										requestRepository.updateStatus(request);
										requestQueue.put(request);
									} else {
										requestRepository.delete(request);
									}
								} else if("deactivation".equalsIgnoreCase(request.getFlowName())){
									if (validation.validateDeActivation(request)) {
										requestRepository.updateStatus(request);
										requestQueue.put(request);
									} else {
										requestRepository.delete(request);

									}
								}else if("songDownload".equalsIgnoreCase(request.getFlowName())){
									if (validation.validateRequest(request)) {
										requestRepository.updateStatus(request);
										requestQueue.put(request);
									} else {
										requestRepository.delete(request);

									}
								}
							}
							logger.info("Source::DataBase " + request);
						}
					}
					Thread.sleep(5000);
				}
			}
		} catch (Exception e) {
			logger.info("Exception in udp Server [" + e + "]");
			e.printStackTrace();
		}
	}
}

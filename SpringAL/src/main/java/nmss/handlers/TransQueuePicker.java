package nmss.handlers;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nmss.base.Request;
import nmss.pojos.crbt.CrbtRequest;
import nmss.util.AppConfig;
import nmss.util.CrbtUtillity;
import nmss.util.JdbcCrbtRequestDAOImpl;
import nmss.util.Validation;

@Component("transQueuePicker")
public class TransQueuePicker implements Runnable {

	@Resource(name = "requestQueue")
	@Autowired
	private LinkedBlockingQueue<Request> requestQueue = null;

	@Autowired
	private Logger lFile = null;

	@Autowired
	private JdbcCrbtRequestDAOImpl jdbcRequestDAOImpl;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private Validation validation;
	
	@Autowired
	private CrbtUtillity crbtUtill;
	

	public void run() {
		try {
			while (true) {
				while (appConfig.isPullingFromDB) {
					List<Request> requestList = jdbcRequestDAOImpl.getAll();
					if (requestList == null) {
						lFile.info("Total Request Selected for fullfilment 0");
					} else {
						for (Request request : requestList) {
							if (request != null) 
							{
								CrbtRequest tempreq = (CrbtRequest)request;
								String ackResponse = tempreq.getMsg();
								ackResponse = ackResponse.replaceAll("REQ", "ACK");
								ackResponse = "00000#5667799#CRBT#"+tempreq.getMsisdn()+"#"+ackResponse;
								crbtUtill.UDP_SEND(appConfig.responseIP, appConfig.responsePORT, ackResponse, true);
								//tempreq.setMsg("");
								if (("activation").equalsIgnoreCase(request.getFlowName())) {
									if (validation.validateActivation(request)) {
										jdbcRequestDAOImpl.updateStatus(request);
										requestQueue.put(request);
									} else {
										String response = crbtUtill.getResponseBack((CrbtRequest) request);
										crbtUtill.UDP_SEND(appConfig.responseIP, appConfig.responsePORT, response, true);
										jdbcRequestDAOImpl.delete(request);
									}

								} else if ("renewal".equalsIgnoreCase(request.getFlowName())) {
									if (validation.validateRenewal(request)) {
										jdbcRequestDAOImpl.updateStatus(request);
										requestQueue.put(request);
									} else {
										jdbcRequestDAOImpl.delete(request);
									}
								} else if("deactivation".equalsIgnoreCase(request.getFlowName())){
									if (validation.validateDeActivation(request)) {
										jdbcRequestDAOImpl.updateStatus(request);
										requestQueue.put(request);
									} else {
										jdbcRequestDAOImpl.delete(request);

									}
								}else if("songDownload".equalsIgnoreCase(request.getFlowName())){
									if (validation.validateRequest(request)) {
										jdbcRequestDAOImpl.updateStatus(request);
										requestQueue.put(request);
									} else {
										jdbcRequestDAOImpl.delete(request);

									}
								}
							}
							lFile.info("Source::DataBase " + request);
						}
					}
					Thread.sleep(5000);
				}
			}
		} catch (Exception e) {
			lFile.info("Exception in udp Server [" + e + "]");
			e.printStackTrace();
		}
	}
}

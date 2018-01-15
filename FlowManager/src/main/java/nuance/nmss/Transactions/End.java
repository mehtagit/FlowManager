package nuance.nmss.Transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import nuance.base.Transaction;
import nuance.base.pojo.Request;
import nuance.nmss.pojos.crbt.CrbtRequest;
import nuance.nmss.util.CrbtConfig;
import nuance.nmss.util.CrbtUtillity;
import nuance.util.FileWriter;

@Component("end")
@Scope("prototype")
public class End extends Transaction {

	@Autowired
	private Gson gson;

	@Autowired
	private CrbtConfig config;

	@Autowired
	private CrbtUtillity crbtUtill;

	@Override
	public boolean doTransaction(Request request) {
		try {
			/**
			 * Writing CDR in file
			 */
			FileWriter.write(request.toCdr().toString());
			
			CrbtRequest crbtRequest = (CrbtRequest) request;
			if ("0".equals(request.getTxnStatus())) {
				if ("activation".equalsIgnoreCase(request.getFlowName())) {
					requestRepository.saveAsSubscribe(crbtRequest);
					requestRepository.updateComplete(crbtRequest);
					String responseBack = crbtUtill.getResponseBack(crbtRequest);
					utility.UDP_SEND(config.getResponseIp(), config.getResponsePort(), responseBack, true);
				} else if ("deactivation".equalsIgnoreCase(request.getFlowName())) {
					requestRepository.deleteFromSubsciption(request);
					requestRepository.updateComplete(crbtRequest);
				} else if ("renewal".equalsIgnoreCase(request.getFlowName())) {
					requestRepository.deleteByTid(request.getTid());
				} else if ("songDownload".equals(request.getFlowName())) {
					// jdbcCrbtRequestDAOImpl.saveAsSubscribe(crbtRequest);
					requestRepository.updateComplete(crbtRequest);
					String responseBack = crbtUtill.getResponseBack(crbtRequest);
					utility.UDP_SEND(config.getResponseIp(), config.getResponsePort(), responseBack, true);

				}
			} else {
				// Fail transactions
			}

			// utility.UDP_SEND(appConfig.cdr_ip, appConfig.cdr_port,
			// request.toCdr(), false);

			/* Response URL Curl with JSON */
			/*
			 * VirginResponse virginResponse = Start.ctx.getBean("virginResponse",
			 * VirginResponse.class); virginResponse.setTid(request.getTid());
			 * virginResponse.setMsisdn(request.getMsisdn()); FmResponse fmResponse =
			 * Start.ctx.getBean("fmResponse", FmResponse.class);
			 * virginResponse.setFmResponse(fmResponse); fmResponse.setCampId("" +
			 * virginRequest.getCampaignParam().getCampId()); if
			 * ("0".equals(request.getTxnStatus())) { String successMsg =
			 * virginRequest.getFulfillment_BenefitParam().getSuccessMsg() == null ?
			 * appConfig.successSms :
			 * virginRequest.getFulfillment_BenefitParam().getSuccessMsg();
			 * fmResponse.setMsg(successMsg); fmResponse.setResult("Success"); } else {
			 * String failMsg = virginRequest.getFulfillment_BenefitParam().getFailSms() ==
			 * null ? appConfig.failSms :
			 * virginRequest.getFulfillment_BenefitParam().getFailSms();
			 * fmResponse.setMsg(failMsg); fmResponse.setResult("fail"); }
			 */
			// utility.sendPost(virginRequest.getUrl(),
			// gson.toJson(virginResponse), "application/json");
			logger.info("End : " + request + ", TimeTake:" + request.getRequestTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}

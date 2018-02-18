package nmss.Transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import nmss.base.Request;
import nmss.base.Transaction;
import nmss.pojos.crbt.CrbtRequest;
import nmss.util.AppConfig;
import nmss.util.CrbtUtillity;
import nmss.util.JdbcCrbtRequestDAOImpl;

@Component("end")
@Scope("prototype")
public class End extends Transaction {

	@Autowired
	private Gson gson;
	
	@Autowired
	private AppConfig config;

	@Autowired
	private CrbtUtillity crbtUtill;

	@Override
	public boolean doTransaction(Request request) {
		try {

			CrbtRequest crbtRequest = (CrbtRequest) request;
			JdbcCrbtRequestDAOImpl jdbcCrbtRequestDAOImpl = (JdbcCrbtRequestDAOImpl) requestDAO;
			if ("0".equals(request.getTxnStatus())) 
			{
				if ("activation".equalsIgnoreCase(request.getFlowName())) 
				{
					jdbcCrbtRequestDAOImpl.saveAsSubscribe(crbtRequest);
					jdbcCrbtRequestDAOImpl.updateComplete(crbtRequest);
					String responseBack = crbtUtill.getResponseBack(crbtRequest);
					utility.UDP_SEND(config.responseIP, config.responsePORT, responseBack, true);				
				}
				else if ("deactivation".equalsIgnoreCase(request.getFlowName())) 
				{
					jdbcCrbtRequestDAOImpl.deleteFromSubsciption(request);
					jdbcCrbtRequestDAOImpl.updateComplete(crbtRequest);
					String responseBack = crbtUtill.getResponseBack(crbtRequest);
					utility.UDP_SEND(config.responseIP, config.responsePORT, responseBack, true);
				}
				else if("renewal".equalsIgnoreCase(request.getFlowName())){
					requestDAO.deleteByTid(request.getTid());
				}else if("songdownload".equals(request.getFlowName())){
					//jdbcCrbtRequestDAOImpl.saveAsSubscribe(crbtRequest);
					jdbcCrbtRequestDAOImpl.updateComplete(crbtRequest);
					String responseBack = crbtUtill.getResponseBack(crbtRequest);
					utility.UDP_SEND(config.responseIP, config.responsePORT, responseBack, true);
				}
			}
			else
			{
				// Fail transactions
			}
			
			 utility.UDP_SEND(appConfig.cdr_ip, appConfig.cdr_port,crbtUtill.getCdrFromXml(crbtRequest), false);

			/* Response URL Curl with JSON */
			/*
			 * VirginResponse virginResponse =
			 * Start.ctx.getBean("virginResponse", VirginResponse.class);
			 * virginResponse.setTid(request.getTid());
			 * virginResponse.setMsisdn(request.getMsisdn()); FmResponse
			 * fmResponse = Start.ctx.getBean("fmResponse", FmResponse.class);
			 * virginResponse.setFmResponse(fmResponse); fmResponse.setCampId(""
			 * + virginRequest.getCampaignParam().getCampId()); if
			 * ("0".equals(request.getTxnStatus())) { String successMsg =
			 * virginRequest.getFulfillment_BenefitParam().getSuccessMsg() ==
			 * null ? appConfig.successSms :
			 * virginRequest.getFulfillment_BenefitParam().getSuccessMsg();
			 * fmResponse.setMsg(successMsg); fmResponse.setResult("Success"); }
			 * else { String failMsg =
			 * virginRequest.getFulfillment_BenefitParam().getFailSms() == null
			 * ? appConfig.failSms :
			 * virginRequest.getFulfillment_BenefitParam().getFailSms();
			 * fmResponse.setMsg(failMsg); fmResponse.setResult("fail"); }
			 */
			// utility.sendPost(virginRequest.getUrl(),
			// gson.toJson(virginResponse), "application/json");
			lFile.info("End : " + request + ", TimeTake:" + request.getRequestTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}

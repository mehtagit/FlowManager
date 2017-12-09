package nmss.Transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import nmss.base.Request;
import nmss.base.Transaction;
import nmss.pojos.crbt.CrbtRequest;
import nmss.util.AppConfig;
import nmss.util.CrbtClientStub;

@Component("token")
@Scope("prototype")
public class Token extends Transaction {

	@Autowired
	private CrbtClientStub crbtClientStub;

	@Autowired
	private AppConfig config;
	
	@Override
	public boolean doTransaction(Request request) {
		try {
			if(config.isStub)
			{
				CrbtRequest crbtRequest = (CrbtRequest) request;
				request.setTxnStatus("0");
				request.setTxnStatusDesc("SUCCESS");
				crbtRequest.setToken("dummy_token");
			}
			else
			{
			crbtClientStub.getToken(request);
			}
			lFile.info("Token Result : " + request + ", TimeTake:" + request.getTxnTime());

			requestDAO.updateState(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}

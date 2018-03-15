package nuance.nmss.Transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import nuance.base.Transaction;
import nuance.base.pojo.Request;
import nuance.nmss.pojos.crbt.CrbtRequest;
import nuance.nmss.util.CrbtClientStub;
import nuance.nmss.util.CrbtConfig;

@Component("token")
@Scope("prototype")
public class Token extends Transaction {

	@Autowired
	private CrbtClientStub crbtClientStub;

	@Autowired
	private CrbtConfig config;

	@Override
	public boolean doTransaction(Request request) {
		try {
			if (config.isStub()) {
				CrbtRequest crbtRequest = (CrbtRequest) request;
				request.setTxnStatus("0");
				request.setTxnStatusDesc("SUCCESS");
				crbtRequest.setToken("dummy_token");
			} else {
				crbtClientStub.getToken(request);
			}
			logger.info("Token Result : " + request + ", TimeTake:" + request.getTxnTime());

			//requestRepository.updateState(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}

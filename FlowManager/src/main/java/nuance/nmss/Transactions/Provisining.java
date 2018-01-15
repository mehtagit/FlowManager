package nuance.nmss.Transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import nuance.base.Transaction;
import nuance.base.pojo.Request;
import nuance.nmss.util.CrbtClientStub;
import nuance.nmss.util.CrbtConfig;

@Component("provisining")
@Scope("prototype")
public class Provisining extends Transaction {

	@Autowired
	private CrbtClientStub crbtClientStub;

	@Autowired
	private CrbtConfig config;

	@Override
	public boolean doTransaction(Request request) {
		try {
			if (config.isStub()) {
				request.setTxnStatus("0");
				request.setTxnStatusDesc("success000");
			} else {
				crbtClientStub.doProvisining(request);
			}

			logger.info("Provisining Result : " + request + ", TimeTake:" + request.getTxnTime());

			//requestRepository.updateState(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}

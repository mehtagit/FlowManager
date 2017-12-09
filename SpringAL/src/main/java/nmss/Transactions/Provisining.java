package nmss.Transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import nmss.base.Request;
import nmss.base.Transaction;
import nmss.util.AppConfig;
import nmss.util.CrbtClientStub;

@Component("provisining")
@Scope("prototype")
public class Provisining extends Transaction {

	@Autowired
	private CrbtClientStub crbtClientStub;

	@Autowired
	private AppConfig config;
	
	@Override
	public boolean doTransaction(Request request) {
		try {
			if (config.isStub)
			{
				request.setTxnStatus("0");
				request.setTxnStatusDesc("success000");
			}
			else
			{
				crbtClientStub.doProvisining(request);
			}
			
			lFile.info("Provisining Result : " + request + ", TimeTake:" + request.getTxnTime());
			
			requestDAO.updateState(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}

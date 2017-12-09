package nmss.Transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import nmss.base.Request;
import nmss.base.Transaction;
import nmss.util.AppConfig;
import nmss.util.CrbtClientStub;

@Component("debit")
@Scope("prototype")
public class Debit extends Transaction {

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
				request.setTxnStatusDesc("SUCCESS");
			}
			else
			{
				crbtClientStub.doDebit(request);
			}

			lFile.info("Debit Result : " + request + ", TimeTake:" + request.getTxnTime());

			requestDAO.updateState(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}

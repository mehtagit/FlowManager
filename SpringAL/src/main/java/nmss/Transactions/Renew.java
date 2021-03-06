package nmss.Transactions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import nmss.base.Request;
import nmss.base.Transaction;
import nmss.util.JdbcCrbtRequestDAOImpl;

@Component("renew")
@Scope("prototype")
public class Renew extends Transaction {

	@Override
	public boolean doTransaction(Request request) {
		try {

			lFile.info("Renew Result : " + request + ", TimeTake:" + request.getTxnTime());

			JdbcCrbtRequestDAOImpl jdbcCrbtRequestDAOImpl = (JdbcCrbtRequestDAOImpl) requestDAO;
			jdbcCrbtRequestDAOImpl.updateRenewal(request);
			jdbcCrbtRequestDAOImpl.renewalSuccessSynk(request);
			requestDAO.updateState(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}

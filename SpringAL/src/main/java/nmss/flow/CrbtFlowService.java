package nmss.flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nmss.Main.Start;
import nmss.Transactions.Balance;
import nmss.Transactions.Credit;
import nmss.Transactions.Debit;
import nmss.Transactions.End;
import nmss.Transactions.Provisining;
import nmss.Transactions.Token;
import nmss.base.Request;
import nmss.base.Service;
import nmss.base.Transaction;
import nmss.util.CrbtUtillity;
import nmss.util.JdbcCrbtRequestDAOImpl;

@Component("activation")
public class CrbtFlowService extends Service {

	@Autowired
	private CrbtUtillity crbtUtillity;

	private JdbcCrbtRequestDAOImpl jdbcCrbtRequestDAOImpl;

	@Override
	public Transaction getNextState(Request request) {
		Transaction txn = null;

		if (request.getTxnName() == null) {
			txn = Start.ctx.getBean("token", Token.class);
		} else {
			switch (request.getTxnName().toLowerCase()) {

			case "start":
				txn = Start.ctx.getBean("token", Token.class);
				request.setTxnName("token");
				break;

			case "token":
				if ("0".equals(request.getTxnStatus())) {
					txn = Start.ctx.getBean("balance", Balance.class);
					request.setTxnName("balance");
				} else {
					int nextRetryMinutes = crbtUtillity.getNextRetryTime(request.getTxnName(),
							request.getRetryCount() + 1);
					if (nextRetryMinutes >= 0) {
						jdbcCrbtRequestDAOImpl.updateNextRetryTime(request, nextRetryMinutes);
						request.plusRetryCount();
					} else {
						txn = Start.ctx.getBean("end", End.class);
						request.setTxnName("end,token");
					}
				}
				break;
			case "balance":
				if ("0".equals(request.getTxnStatus())) {
					txn = Start.ctx.getBean("debit", Debit.class);
					request.setTxnName("debit");
				} else {
					int nextRetryMinutes = crbtUtillity.getNextRetryTime(request.getTxnName(),
							request.getRetryCount() + 1);
					if (nextRetryMinutes >= 0) {
						jdbcCrbtRequestDAOImpl.updateNextRetryTime(request, nextRetryMinutes);
						request.plusRetryCount();
					} else {
						txn = Start.ctx.getBean("end", End.class);
						request.setTxnName("end,balance");
					}
				}
				break;

			case "debit":
				if ("0".equals(request.getTxnStatus())) {
					txn = Start.ctx.getBean("provisining", Provisining.class);
					request.setTxnName("provisining");
				} else {
					int nextRetryMinutes = crbtUtillity.getNextRetryTime(request.getTxnName(),
							request.getRetryCount() + 1);
					if (nextRetryMinutes >= 0) {
						jdbcCrbtRequestDAOImpl.updateNextRetryTime(request, nextRetryMinutes);
						request.plusRetryCount();
					} else {
						txn = Start.ctx.getBean("end", End.class);
						request.setTxnName("end,debit");
					}
				}
				break;
			case "provisining":
				if ("0".equals(request.getTxnStatus())) {
					txn = Start.ctx.getBean("end", End.class);
					request.setTxnName("end");
				} else {
					int nextRetryMinutes = crbtUtillity.getNextRetryTime(request.getTxnName(),
							request.getRetryCount() + 1);
					if (nextRetryMinutes >= 0) {
						jdbcCrbtRequestDAOImpl.updateNextRetryTime(request, nextRetryMinutes);
						request.plusRetryCount();
					} else {
						txn = Start.ctx.getBean("credit", Credit.class);
						request.setTxnName("credit");
					}
				}
				break;
			case "credit":
				if ("0".equals(request.getTxnStatus())) {
					txn = Start.ctx.getBean("end", End.class);
					request.setTxnName("end");
				} else {
					int nextRetryMinutes = crbtUtillity.getNextRetryTime(request.getTxnName(),
							request.getRetryCount() + 1);
					if (nextRetryMinutes >= 0) {
						jdbcCrbtRequestDAOImpl.updateNextRetryTime(request, nextRetryMinutes);
						request.plusRetryCount();
					} else {
						txn = Start.ctx.getBean("credit", Credit.class);
						request.setTxnName("credit");
					}
				}
				break;

			default:
				lFile.info("No Transaction found for " + request);
			}
		}

		return txn;
	}

}

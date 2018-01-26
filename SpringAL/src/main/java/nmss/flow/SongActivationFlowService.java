package nmss.flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import nmss.Main.Start;
import nmss.Transactions.Balance;
import nmss.Transactions.Credit;
import nmss.Transactions.Debit;
import nmss.Transactions.End;
import nmss.Transactions.Provisining;
import nmss.Transactions.SongBalance;
import nmss.Transactions.Token;
import nmss.base.Request;
import nmss.base.Service;
import nmss.base.Transaction;
import nmss.util.CrbtUtillity;
import nmss.util.JdbcCrbtRequestDAOImpl;

@Component("songdownload")
public class SongActivationFlowService extends Service {

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
					txn = Start.ctx.getBean("songBalance", SongBalance.class);
					request.setTxnName("songBalance");
				} else {
					int nextRetryMinutes = crbtUtillity.getNextRetryTime(request.getTxnName(),
							request.getRetryCount() + 1);
					if (nextRetryMinutes >= 0) {
						jdbcCrbtRequestDAOImpl.updateNextRetryTime(request, nextRetryMinutes);
						request.plusRetryCount();
					} else {
						txn = Start.ctx.getBean("end", End.class);
						request.setTxnName("end");
					}
				}
				break;
			case "songbalance":
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
						request.setTxnName("end");
					}
				}
				break;

			case "debit":
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
						txn = Start.ctx.getBean("debit", Debit.class);
						request.setTxnName("debit");
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

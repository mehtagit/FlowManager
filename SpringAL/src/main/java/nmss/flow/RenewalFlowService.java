package nmss.flow;

import org.springframework.stereotype.Component;

import nmss.Main.Start;
import nmss.Transactions.Balance;
import nmss.Transactions.DeProvisining;
import nmss.Transactions.Debit;
import nmss.Transactions.End;
import nmss.Transactions.Grace;
import nmss.Transactions.Renew;
import nmss.Transactions.Token;
import nmss.base.Request;
import nmss.base.Service;
import nmss.base.Transaction;

@Component("renewal")
public class RenewalFlowService extends Service {

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
					txn = Start.ctx.getBean("end", End.class);
					request.setTxnName("end,balance");
				}
				break;

			case "balance":
				if ("0".equals(request.getTxnStatus())) {
					txn = Start.ctx.getBean("debit", Debit.class);
					request.setTxnName("debit");
				} else if ("3".equals(request.getTxnStatus()) || "4".equals(request.getTxnStatus())) {
					txn = Start.ctx.getBean("grace", Grace.class);
					request.setTxnName("grace");
				} else {
					txn = Start.ctx.getBean("deprovisining", DeProvisining.class);
					request.setTxnName("deprovisining");
				}
				break;

			case "grace":
				if ("0".equals(request.getTxnStatus())) {
					txn = Start.ctx.getBean("end", End.class);
					request.setTxnName("end");
				} else {
					txn = Start.ctx.getBean("end", End.class);
					request.setTxnName("end");
				}
				break;

			case "debit":
				if ("0".equals(request.getTxnStatus())) {
					txn = Start.ctx.getBean("renew", Renew.class);
					request.setTxnName("renew");
				} else {
					txn = Start.ctx.getBean("deprovisining", DeProvisining.class);
					request.setTxnName("deprovisining");
				}
				break;

			case "deprovisining":
				if ("0".equals(request.getTxnStatus())) {
					txn = Start.ctx.getBean("end", End.class);
					request.setTxnName("end");
				} else {
					txn = Start.ctx.getBean("end", End.class);
					request.setTxnName("end");
				}
				break;

			default:
				lFile.info("No Transaction found for " + request);
			}
		}

		return txn;
	}

}

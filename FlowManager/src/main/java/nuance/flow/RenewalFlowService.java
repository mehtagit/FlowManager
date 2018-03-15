package nuance.flow;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import nuance.base.Service;
import nuance.base.Transaction;
import nuance.base.pojo.Request;
import nuance.nmss.Transactions.Balance;
import nuance.nmss.Transactions.DeProvisining;
import nuance.nmss.Transactions.Debit;
import nuance.nmss.Transactions.End;
import nuance.nmss.Transactions.Grace;
import nuance.nmss.Transactions.Renew;
import nuance.nmss.Transactions.Token;
import nuance.util.ApplicationContextProvider;

@Component("renewal")
public class RenewalFlowService extends Service {

	protected final Logger logger = LogManager.getLogger(DeactivateFlowService.class);

	@Override
	public Transaction getNextState(Request request) {
		Transaction txn = null;

		if (request.getTxnName() == null) {
			txn = ApplicationContextProvider.getBean("token", Token.class);
		} else {
			switch (request.getTxnName().toLowerCase()) {

			case "start":
				txn = ApplicationContextProvider.getBean("token", Token.class);
				request.setTxnName("token");
				break;

			case "token":
				if ("0".equals(request.getTxnStatus())) {
					txn = ApplicationContextProvider.getBean("balance", Balance.class);
					request.setTxnName("balance");
				} else {
					txn = ApplicationContextProvider.getBean("end", End.class);
					request.setTxnName("end,balance");
				}
				break;

			case "balance":
				if ("0".equals(request.getTxnStatus())) {
					txn = ApplicationContextProvider.getBean("debit", Debit.class);
					request.setTxnName("debit");
				} else if ("3".equals(request.getTxnStatus()) || "4".equals(request.getTxnStatus())) {
					txn = ApplicationContextProvider.getBean("grace", Grace.class);
					request.setTxnName("grace");
				} else {
					txn = ApplicationContextProvider.getBean("deprovisining", DeProvisining.class);
					request.setTxnName("deprovisining");
				}
				break;

			case "grace":
				if ("0".equals(request.getTxnStatus())) {
					txn = ApplicationContextProvider.getBean("end", End.class);
					request.setTxnName("end");
				} else {
					txn = ApplicationContextProvider.getBean("end", End.class);
					request.setTxnName("end");
				}
				break;

			case "debit":
				if ("0".equals(request.getTxnStatus())) {
					txn = ApplicationContextProvider.getBean("renew", Renew.class);
					request.setTxnName("renew");
				} else {
					txn = ApplicationContextProvider.getBean("deprovisining", DeProvisining.class);
					request.setTxnName("deprovisining");
				}
				break;

			case "deprovisining":
				if ("0".equals(request.getTxnStatus())) {
					txn = ApplicationContextProvider.getBean("end", End.class);
					request.setTxnName("end");
				} else {
					txn = ApplicationContextProvider.getBean("end", End.class);
					request.setTxnName("end");
				}
				break;

			default:
				logger.info("No Transaction found for " + request);
			}
		}

		return txn;
	}

}

package nuance.flow;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import nuance.Start;
import nuance.base.Service;
import nuance.base.Transaction;
import nuance.base.pojo.Request;
import nuance.nmss.Transactions.DeProvisining;
import nuance.nmss.Transactions.End;
import nuance.util.ApplicationContextProvider;

@Component("deactivation")
public class DeactivateFlowService extends Service {
	protected final Logger logger = LogManager.getLogger(DeactivateFlowService.class);

	@Override
	public Transaction getNextState(Request request) {
		Transaction txn = null;

		if (request.getTxnName() == null) {
			txn = ApplicationContextProvider.getBean("deProvisining", DeProvisining.class);
			request.setTxnName("deprovisining");
		} else {
			switch (request.getTxnName().toLowerCase()) {

			case "start":
				txn = ApplicationContextProvider.getBean("deprovisining", DeProvisining.class);
				request.setTxnName("deprovisining");
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

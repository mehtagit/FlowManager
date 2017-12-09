package nmss.flow;

import org.springframework.stereotype.Component;

import nmss.Main.Start;
import nmss.Transactions.DeProvisining;
import nmss.Transactions.End;
import nmss.base.Request;
import nmss.base.Service;
import nmss.base.Transaction;

@Component("deactivation")
public class DeactivateFlowService extends Service {

	@Override
	public Transaction getNextState(Request request) {
		Transaction txn = null;

		if (request.getTxnName() == null) {
			txn = Start.ctx.getBean("deProvisining", DeProvisining.class);
			request.setTxnName("deprovisining");
		} else {
			switch (request.getTxnName().toLowerCase()) {

			case "start":
				txn = Start.ctx.getBean("deprovisining", DeProvisining.class);
				request.setTxnName("deprovisining");
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

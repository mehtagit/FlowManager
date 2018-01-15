package nuance.nmss.Transactions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import nuance.base.Transaction;
import nuance.base.pojo.Request;

@Component("renew")
@Scope("prototype")
public class Renew extends Transaction {

	@Override
	public boolean doTransaction(Request request) {
		try {

			logger.info("Grace Result : " + request + ", TimeTake:" + request.getTxnTime());

			requestRepository.updateRenewal(request);

			//requestRepository.updateState(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}

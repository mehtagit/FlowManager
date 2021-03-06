package nuance.flow;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.log.Log;

import nuance.base.Service;
import nuance.base.Transaction;
import nuance.base.pojo.Request;
import nuance.nmss.Transactions.Balance;
import nuance.nmss.Transactions.Credit;
import nuance.nmss.Transactions.Debit;
import nuance.nmss.Transactions.End;
import nuance.nmss.Transactions.Provisining;
import nuance.nmss.Transactions.Token;
import nuance.nmss.util.CrbtUtillity;
import nuance.repository.RequestRepository;
import nuance.util.ApplicationContextProvider;

@Component("activation")
public class CrbtFlowService extends Service {

	@Autowired
	private CrbtUtillity crbtUtillity;

	@Autowired
	private RequestRepository requestRepository;
	
	protected final Logger logger = LogManager.getLogger(CrbtFlowService.class);

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
					int nextRetryMinutes = crbtUtillity.getNextRetryTime(request.getTxnName(),
							request.getRetryCount() + 1);
					if (nextRetryMinutes >= 0) {
						requestRepository.updateNextRetryTime(request, nextRetryMinutes);
						request.plusRetryCount();
					} else {
						txn = ApplicationContextProvider.getBean("end", End.class);
						request.setTxnName("end,token");
					}
				}
				break;
			case "balance":
				if ("0".equals(request.getTxnStatus())) {
					txn = ApplicationContextProvider.getBean("debit", Debit.class);
					request.setTxnName("debit");
				} else {
					int nextRetryMinutes = crbtUtillity.getNextRetryTime(request.getTxnName(),
							request.getRetryCount() + 1);
					if (nextRetryMinutes >= 0) {
						requestRepository.updateNextRetryTime(request, nextRetryMinutes);
						request.plusRetryCount();
					} else {
						txn = ApplicationContextProvider.getBean("end", End.class);
						request.setTxnName("end,balance");
					}
				}
				break;

			case "debit":
				if ("0".equals(request.getTxnStatus())) {
					txn = ApplicationContextProvider.getBean("provisining", Provisining.class);
					request.setTxnName("provisining");
				} else {
					int nextRetryMinutes = crbtUtillity.getNextRetryTime(request.getTxnName(),
							request.getRetryCount() + 1);
					if (nextRetryMinutes >= 0) {
						requestRepository.updateNextRetryTime(request, nextRetryMinutes);
						request.plusRetryCount();
					} else {
						txn = ApplicationContextProvider.getBean("end", End.class);
						request.setTxnName("end,debit");
					}
				}
				break;
			case "provisining":
				if ("0".equals(request.getTxnStatus())) {
					txn = ApplicationContextProvider.getBean("end", End.class);
					request.setTxnName("end");
				} else {
					int nextRetryMinutes = crbtUtillity.getNextRetryTime(request.getTxnName(),
							request.getRetryCount() + 1);
					if (nextRetryMinutes >= 0) {
						requestRepository.updateNextRetryTime(request, nextRetryMinutes);
						request.plusRetryCount();
					} else {
						txn = ApplicationContextProvider.getBean("credit", Credit.class);
						request.setTxnName("credit");
					}
				}
				break;
			case "credit":
				if ("0".equals(request.getTxnStatus())) {
					txn = ApplicationContextProvider.getBean("end", End.class);
					request.setTxnName("end");
				} else {
					int nextRetryMinutes = crbtUtillity.getNextRetryTime(request.getTxnName(),
							request.getRetryCount() + 1);
					if (nextRetryMinutes >= 0) {
						requestRepository.updateNextRetryTime(request, nextRetryMinutes);
						request.plusRetryCount();
					} else {
						txn = ApplicationContextProvider.getBean("credit", Credit.class);
						request.setTxnName("credit");
					}
				}
				break;

			default:
				logger.info("No Transaction found for " + request);
			}
		}

		return txn;
	}

}

package nmss.Transactions;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import nmss.base.Request;
import nmss.base.Transaction;
import nmss.pojos.crbt.CrbtRequest;
import nmss.pojos.crbt.PackDetails;
import nmss.util.AppConfig;
import nmss.util.CrbtClientStub;

@Component("balance")
@Scope("prototype")
public class Balance extends Transaction {

	@Autowired
	private CrbtClientStub crbtClientStub;

	@Autowired
	private ConcurrentHashMap<String, List<PackDetails>> mapOfFallBack;

	@Autowired
	private AppConfig config;
	
	@Override
	public boolean doTransaction(Request request) {
		try {
			if (config.isStub)
			{
				CrbtRequest crbtRequest = (CrbtRequest) request;
				crbtRequest.setAvailableBalance(-1000f);
				request.setTxnStatus("0");
				request.setTxnStatusDesc("SUCCESS");
				crbtRequest.setSimType("Prepaid");
				
			}
			else
			{
				crbtClientStub.getBalance(request);
			}

			CrbtRequest crbtRequest = (CrbtRequest) request;
			List<PackDetails> fallBackList = mapOfFallBack.get(crbtRequest.getPackId());
			// Checking Fallbacks
			if (crbtRequest.getAvailableBalance() <= 0) {
				float availBal = -crbtRequest.getAvailableBalance();

				for (PackDetails packDetails : fallBackList) {
					/* negative balance means balance is available */
					crbtRequest.setDaysCanGrace(packDetails.getGraceDays());
					if (packDetails.getAmount() <= availBal) {
						crbtRequest.setDebitAmount(packDetails.getAmount());
						crbtRequest.setCreditAmout(packDetails.getAmount());
						crbtRequest.setDays(packDetails.getDays());
						crbtRequest.setDaysCanGrace(packDetails.getGraceDays());
						break;
					}
				}
				if(crbtRequest.getDaysGraced() > crbtRequest.getDaysCanGrace() ){
					request.setTxnStatus("2");
					request.setTxnStatusDesc("Low Balance and Graced also finished");
				}
				else if(crbtRequest.getDebitAmount() <= 0){
					request.setTxnStatus("4");
					request.setTxnStatusDesc("Low Balance");
				}
			} else {
				request.setTxnStatus("3");
				request.setTxnStatusDesc("No Balance in Account");
			}

			lFile.info("Balance Result : " + request + ", TimeTake:" + request.getTxnTime());

			requestDAO.updateState(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}

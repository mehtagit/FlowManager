package nmss.util;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import nmss.base.Request;
import nmss.pojos.crbt.CrbtRequest;
import nmss.pojos.crbt.client.BalanceResponse;
import nmss.pojos.crbt.client.DebitResponse;
import nmss.pojos.crbt.client.TokenResponse;
import nmss.pojos.crbt.client.TxBank;

@Component("crbtClientStub")
public class CrbtClientStub {

	@Autowired
	protected AppConfig appConfig;

	@Autowired
	private Logger lFile = null;

	@Autowired
	private Utility utility;

	@Autowired
	private Gson gson;

	public void validate() {

	}

	@PostConstruct
	public void postConstruct() {
	}

	public String getAuthCredentials() {
		return null;
	}

	public boolean getToken(Request req) {
		CrbtRequest request = (CrbtRequest) req;
		boolean status = false;
		String fireTokenUrl = appConfig.tokenUrl;
		try {
			String tokenResult = utility.callUrl(fireTokenUrl);
			if (tokenResult == null) {
				request.setTxnStatus("1");
				request.setTxnStatusDesc("URL-Not-Called");
			} else {
				TokenResponse tokenResponse = gson.fromJson(tokenResult, TokenResponse.class);

				if (tokenResponse.getReturnResult() == null || tokenResponse.getReturnResult().equals("")) {
					request.setToken(tokenResponse.getReturnResult());
					request.setTxnStatus("2");
					request.setTxnStatusDesc("TOKEN FAIL");
					status = true;

				} else {
					request.setToken(tokenResponse.getReturnResult());
					request.setTxnStatus("0");
					request.setTxnStatusDesc("SUCCESS");
					status = true;
				}
			}

		} catch (Exception e) {
			request.setTxnStatus("1000");
			request.setTxnStatusDesc(e.getMessage());
			e.printStackTrace();
		}
		lFile.info("Token Parameters Fired URL:: Msisdn:" + request.getMsisdn() + ", " + request);

		return status;
	}

	public boolean getBalance(Request req) {
		CrbtRequest request = (CrbtRequest) req;
		boolean status = false;
		String fireBalanceUrl = appConfig.balanceUrl;

		try {
			fireBalanceUrl = fireBalanceUrl.replaceAll("<identifier>", request.getMsisdn());
			fireBalanceUrl = fireBalanceUrl.replaceAll("<token>", request.getToken());

			String balanceResult = utility.callUrl(fireBalanceUrl);

			BalanceResponse balanceResponse = gson.fromJson(balanceResult, BalanceResponse.class);

			if (balanceResponse.getReturnCode() == 0) {
				for (TxBank txBank : balanceResponse.getReturnResult().get(0).getTxBanks()) {
					lFile.debug("BankName:" + txBank.getBankName() + ", Bal:" + txBank.getBalance());
					if ("Postpaid_Credit".equals(txBank.getBankName())) {
						request.setAvailableBalance(txBank.getBalance());
						request.setTxnStatus("0");
						request.setTxnStatusDesc("SUCCESS");
						request.setSimType("Postpaid");
						status = true;
						break;
					}
					if ("Prepaid_Credit".equals(txBank.getBankName())) {
						request.setAvailableBalance(txBank.getBalance());
						request.setTxnStatus("0");
						request.setTxnStatusDesc("SUCCESS");
						request.setSimType("Prepaid");
						status = true;
					}
				}

				if (!status) {
					request.setTxnStatus("1");
					request.setTxnStatusDesc("Prepaid_Credit/Postpaid_Credit Bank Not Found");
				}
			} else {
				request.setTxnStatus(balanceResponse.getReturnCode() + "");
				request.setTxnStatusDesc(balanceResponse.getReturnMessage());
			}

		} catch (Exception e) {
			request.setTxnStatus("1000");
			request.setTxnStatusDesc(e.getMessage());
			e.printStackTrace();
		}
		lFile.info("Balance Parameters Fired URL:: Msisdn:" + request.getMsisdn() + ", " + request);

		return status;
	}

	public boolean doDebit(Request req) {
		CrbtRequest request = (CrbtRequest) req;
		boolean status = false;
		String fireDebitUrl = appConfig.debitUrl;

		try {
			fireDebitUrl = fireDebitUrl.replaceAll("<identifier>", request.getMsisdn());
			fireDebitUrl = fireDebitUrl.replaceAll("<token>", request.getToken());
			fireDebitUrl = fireDebitUrl.replaceAll("<AMT>", "" + request.getDebitAmount());
			fireDebitUrl = fireDebitUrl.replaceAll("<datetime>", utility.getDateTime());
			fireDebitUrl = fireDebitUrl.replaceAll("<SIM>", request.getSimType());

			String debitResult = utility.callUrl(fireDebitUrl);

			DebitResponse debitResponse = gson.fromJson(debitResult, DebitResponse.class);

			if (debitResponse.getReturnCode() == 0 && "Event executed".equals(debitResponse.getReturnMessage())) {
				request.setTxnStatus("0");
				request.setTxnStatusDesc("SUCCESS");
				status = true;

			} else {
				request.setTxnStatus(debitResponse.getReturnCode() + "");
				request.setTxnStatusDesc(debitResponse.getReturnMessage());
			}

		} catch (Exception e) {
			request.setTxnStatus("1000");
			request.setTxnStatusDesc(e.getMessage());
			e.printStackTrace();
		}
		lFile.info("Debit Parameters Fired URL:: Msisdn:" + request.getMsisdn() + ", " + request);

		return status;
	}

	public boolean doProvisining(Request req) {
		CrbtRequest request = (CrbtRequest) req;
		boolean status = false;
		try {

			String activatePacket = appConfig.activatePacket.replaceAll("<identifier>", request.getMsisdn());
			activatePacket = activatePacket.replaceAll("<tid>", request.getTid());

			String provisiningResponse = utility.sendReceive(activatePacket, appConfig.provisningIp,
					appConfig.provisningPort);
			// 00041#serviceid##5016704184#SUCCESS,123456789#
			if (provisiningResponse == null) {
				request.setTxnStatus("1");
				request.setTxnStatusDesc("ProvisiningResponse not recieved");
			} else {
				try {
					String result = provisiningResponse.split("#")[4].split(",")[0];
					request.setTxnStatusDesc(result);
					if ("SUCCESS".equals(result)) {
						request.setTxnStatus("0");
					} else {
						request.setTxnStatus("-1");
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					request.setTxnStatus("2");
					request.setTxnStatusDesc("Wrong Provisining Response received");
				}
			}

		} catch (Exception e) {
			request.setTxnStatus("1000");
			request.setTxnStatusDesc(e.getMessage());
		}
		lFile.info("Provisning Parameters Fired URL:: Msisdn:" + request.getMsisdn() + " " + request);
		return status;
	}

	public boolean doCredit(Request req) {
		CrbtRequest request = (CrbtRequest) req;
		boolean status = false;
		try {
			request.setTxnStatus("0");
			request.setTxnStatusDesc("SUCCESS");

		} catch (Exception e) {
			request.setTxnStatus("1000");
			request.setTxnStatusDesc(e.getMessage());
		}
		lFile.info("Credit Parameters Fired URL:: Msisdn:" + request.getMsisdn() + " " + request);
		return status;
	}

	public boolean doDeProvisining(Request req) {
		CrbtRequest request = (CrbtRequest) req;
		boolean status = false;
		try {

			String activatePacket = appConfig.deactivatePacket.replaceAll("<identifier>", request.getMsisdn());
			activatePacket = activatePacket.replaceAll("<tid>", request.getTid());

			String provisiningResponse = utility.sendReceive(activatePacket, appConfig.provisningIp,
					appConfig.provisningPort);
			// 00041#serviceid##5016704184#SUCCESS,123456789#
			if (provisiningResponse == null) {
				request.setTxnStatus("1");
				request.setTxnStatusDesc("ProvisiningResponse not recieved");
			} else {
				try {
					String result = provisiningResponse.split("#")[4].split(",")[0];
					request.setTxnStatusDesc(result);
					if ("SUCCESS".equals(result)) {
						request.setTxnStatus("0");
					} else {
						request.setTxnStatus("-1");
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					request.setTxnStatus("2");
					request.setTxnStatusDesc("Wrong Provisining Response received");
				}
			}

		} catch (Exception e) {
			request.setTxnStatus("1000");
			request.setTxnStatusDesc(e.getMessage());
		}
		lFile.info("Provisning Parameters Fired URL:: Msisdn:" + request.getMsisdn() + " " + request);
		return status;

	}
}

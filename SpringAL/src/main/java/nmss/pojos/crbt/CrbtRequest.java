package nmss.pojos.crbt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import nmss.base.Request;
import nmss.util.XmlParser;

@Component("airtelRequest")
@Scope("prototype")
public class CrbtRequest extends Request {
	
	@Autowired
	private XmlParser xmlParser;
	

	private String token;
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private float debitAmount;
	private float creditAmout;
	private float availableBalance;
	private String packId;
	private int days;
	private String simType;
	private int daysGraced;
	private int daysCanGrace;

	public String getSimType() {
		return simType;
	}

	public void setSimType(String simType) {
		this.simType = simType;
	}

	public float getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(float debitAmount) {
		this.debitAmount = debitAmount;
	}

	public float getCreditAmout() {
		return creditAmout;
	}

	public void setCreditAmout(float creditAmout) {
		this.creditAmout = creditAmout;
	}

	public float getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(float availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPackId() {
		return packId;
	}

	public void setPackId(String packId) {
		this.packId = packId;
	}

	@Override
	public String toCdr() {
		return "";
	}

	public int getDaysGraced() {
		return daysGraced;
	}

	public void setDaysGraced(int daysGraced) {
		this.daysGraced = daysGraced;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getDaysCanGrace() {
		return daysCanGrace;
	}

	public void setDaysCanGrace(int daysCanGrace) {
		this.daysCanGrace = daysCanGrace;
	}

	@Override
	public String toString() {
		return "CrbtRequest [token=" + token + ", debitAmount=" + debitAmount + ", creditAmout=" + creditAmout
				+ ", availableBalance=" + availableBalance + ", packId=" + packId + ", days=" + days + ", simType="
				+ simType + ", daysGraced=" + daysGraced + ", daysCanGrace=" + daysCanGrace + ", Msisdn="
				+ getMsisdn() + ", Tid=" + getTid() + ", TxnName=" + getTxnName() + ", TxnStatus="
				+ getTxnStatus() + ", TxnStatusDesc=" + getTxnStatusDesc() + ", FlowName=" + getFlowName()
				+ "]";
	}
	
	public String getDateTime()
	{
		return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	}
}

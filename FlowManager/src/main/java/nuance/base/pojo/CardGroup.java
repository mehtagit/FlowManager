package nuance.base.pojo;

public class CardGroup {
	private String name;
	private String value;
	private String type;
	private Float amount;
	private String debitCode;
	private String creditCode;
	private String refundCode;
	private String externalData1;
	private String externalData2;
	private String expiryDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getDebitCode() {
		return debitCode;
	}

	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getExternalData1() {
		return externalData1;
	}

	public String getRefundCode() {
		return refundCode;
	}

	public void setRefundCode(String refundCode) {
		this.refundCode = refundCode;
	}

	public void setExternalData1(String externalData1) {
		this.externalData1 = externalData1;
	}

	public String getExternalData2() {
		return externalData2;
	}

	public void setExternalData2(String externalData2) {
		this.externalData2 = externalData2;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "CardGroup [name=" + name + ", value=" + value + ", type=" + type + ", amount=" + amount + ", debitCode="
				+ debitCode + ", creditCode=" + creditCode + ", ucipRefundCode=" + refundCode + ", externalData1="
				+ externalData1 + ", externalData2=" + externalData2 + ", expiryDate=" + expiryDate + "]";
	}

}

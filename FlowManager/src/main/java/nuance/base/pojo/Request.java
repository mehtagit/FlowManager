package nuance.base.pojo;

import java.util.StringJoiner;

abstract public class Request {

	private Long id;
	private String msisdn;
	private String tid;
	private CardGroup cardgroup;
	private Fulfillment fulfilment;
	private Campaign campaign;
	private String responseUrl;
	private String ip;
	private Integer port;
	private int retryCount;

	/* Flow Management Parameters */
	private String flowName;
	private String txnName;
	private String txnStatus;
	private int status;
	private String txnStatusDesc;
	private String nextRetryTime;
	private long requestStartTime;
	private long txnStartTime;
	/* Flow Management Parameters */

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTxnName() {
		return txnName;
	}

	public void setTxnName(String txnName) {
		this.txnName = txnName;
	}

	public String getTxnStatus() {
		return txnStatus;
	}

	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}

	public String getTxnStatusDesc() {
		return txnStatusDesc;
	}

	public void setTxnStatusDesc(String txnStatusDesc) {
		this.txnStatusDesc = txnStatusDesc;
	}

	public String getNextRetryTime() {
		return nextRetryTime;
	}

	public void setNextRetryTime(String nextRetryTime) {
		this.nextRetryTime = nextRetryTime;
	}

	public void startTime() {
		this.requestStartTime = System.currentTimeMillis();
	}

	public void txnStartTime() {
		txnStartTime = System.currentTimeMillis();
	}

	public long getTxnTime() {
		return System.currentTimeMillis() - txnStartTime;
	}

	public long getRequestTime() {
		return System.currentTimeMillis() - requestStartTime;
	}

	public String getFlowName() {
		return flowName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public CardGroup getCardgroup() {
		return cardgroup;
	}

	public void setCardgroup(CardGroup cardgroup) {
		this.cardgroup = cardgroup;
	}

	public Fulfillment getFulfilment() {
		return fulfilment;
	}

	public void setFulfilment(Fulfillment fulfillment) {
		this.fulfilment = fulfillment;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	public String getResponseUrl() {
		return responseUrl;
	}

	public void setResponseUrl(String responseUrl) {
		this.responseUrl = responseUrl;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public long getRequestStartTime() {
		return requestStartTime;
	}

	public void setRequestStartTime(long requestStartTime) {
		this.requestStartTime = requestStartTime;
	}

	public long getTxnStartTime() {
		return txnStartTime;
	}

	public void setTxnStartTime(long txnStartTime) {
		this.txnStartTime = txnStartTime;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void plusRetryCount() {
		this.retryCount += 1;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", msisdn=" + msisdn + ", tid=" + tid + ", cardGroup=" + cardgroup
				+ ", fulfillment=" + fulfilment + ", campaign=" + campaign + ", responseUrl=" + responseUrl + ", ip="
				+ ip + ", port=" + port + ", flowName=" + flowName + ", txnName=" + txnName + ", txnStatus=" + txnStatus
				+ ", status=" + status + ", txnStatusDesc=" + txnStatusDesc + ", nextRetryTime=" + nextRetryTime
				+ ", requestStartTime=" + requestStartTime + ", txnStartTime=" + txnStartTime + "]";
	}

	public StringJoiner toCdr() {
		StringJoiner cdr = new StringJoiner("#");
		cdr = cdr.add(getTid()).add(getMsisdn()).add(getCardgroup() == null ? "" : getCardgroup().getName())
				.add(getCardgroup() == null ? "" : getCardgroup().getType())
				.add(getCardgroup() == null ? "" : getCardgroup().getValue())
				.add(getCardgroup() == null ? "" : getCardgroup().getAmount() + "").add(getTxnName())
				.add(getTxnStatus()).add(getTxnStatusDesc()).add(getCampaign() == null ? "" : getCampaign().getCampId())
				.add(getCampaign() == null ? "" : getCampaign().getType())
				.add(getCampaign() == null ? "" : getCampaign().getStartDate())
				.add(getCampaign() == null ? "" : getCampaign().getEndDate())
				.add(getFulfilment() == null ? "" : getFulfilment().getId());
		return cdr;
	}
}

package nmss.base;

abstract public class Request {

	private String msisdn;
	private String tid;

	private String flowName;
	private String txnName;
	private String txnStatus;
	private String txnStatusDesc;
	private int retryCount;
	private String nextRetryTime;
	private long requestStartTime;
	private long txnStartTime;

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

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void plusRetryCount() {
		this.retryCount += 1;
	}

	abstract public String toCdr();
}

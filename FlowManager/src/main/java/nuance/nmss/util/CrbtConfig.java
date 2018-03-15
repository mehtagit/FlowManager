package nuance.nmss.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "crbt")
public class CrbtConfig {

	private String provisningIp;

	private int provisningPort;

	private String activatePacket;

	private String deactivatePacket;

	private String tokenUrl;

	private String balanceUrl;

	private String debitUrl;

	private int serviceThreadPoolSize;

	private String retryAckRequestLogic;

	private int transactionTps;

	private int responseUrlReadtimeout;

	private int responseUrlConnecttimeout;

	private boolean isStub;

	private boolean retryAck;

	private String responseIp;

	private int responsePort;

	private int ackUdpServerPort;

	private boolean isPullingFromDb = true;

	public String getProvisningIp() {
		return provisningIp;
	}

	public void setProvisningIp(String provisningIp) {
		this.provisningIp = provisningIp;
	}

	public int getProvisningPort() {
		return provisningPort;
	}

	public void setProvisningPort(int provisningPort) {
		this.provisningPort = provisningPort;
	}

	public String getActivatePacket() {
		return activatePacket;
	}

	public void setActivatePacket(String activatePacket) {
		this.activatePacket = activatePacket;
	}

	public String getDeactivatePacket() {
		return deactivatePacket;
	}

	public void setDeactivatePacket(String deactivatePacket) {
		this.deactivatePacket = deactivatePacket;
	}

	public String getTokenUrl() {
		return tokenUrl;
	}

	public void setTokenUrl(String tokenUrl) {
		this.tokenUrl = tokenUrl;
	}

	public String getBalanceUrl() {
		return balanceUrl;
	}

	public void setBalanceUrl(String balanceUrl) {
		this.balanceUrl = balanceUrl;
	}

	public String getDebitUrl() {
		return debitUrl;
	}

	public void setDebitUrl(String debitUrl) {
		this.debitUrl = debitUrl;
	}

	public int getServiceThreadPoolSize() {
		return serviceThreadPoolSize;
	}

	public void setServiceThreadPoolSize(int serviceThreadPoolSize) {
		this.serviceThreadPoolSize = serviceThreadPoolSize;
	}

	public String getRetryAckRequestLogic() {
		return retryAckRequestLogic;
	}

	public void setRetryAckRequestLogic(String retryAckRequestLogic) {
		this.retryAckRequestLogic = retryAckRequestLogic;
	}

	public int getTransactionTps() {
		return transactionTps;
	}

	public void setTransactionTps(int transactionTps) {
		this.transactionTps = transactionTps;
	}

	public int getResponseUrlReadtimeout() {
		return responseUrlReadtimeout;
	}

	public void setResponseUrlReadtimeout(int responseUrlReadtimeout) {
		this.responseUrlReadtimeout = responseUrlReadtimeout;
	}

	public int getResponseUrlConnecttimeout() {
		return responseUrlConnecttimeout;
	}

	public void setResponseUrlConnecttimeout(int responseUrlConnecttimeout) {
		this.responseUrlConnecttimeout = responseUrlConnecttimeout;
	}

	public boolean isStub() {
		return isStub;
	}

	public void setStub(boolean isStub) {
		this.isStub = isStub;
	}

	public boolean isRetryAck() {
		return retryAck;
	}

	public void setRetryAck(boolean retryAck) {
		this.retryAck = retryAck;
	}

	public String getResponseIp() {
		return responseIp;
	}

	public void setResponseIp(String responseIp) {
		this.responseIp = responseIp;
	}

	public int getResponsePort() {
		return responsePort;
	}

	public void setResponsePort(int responsePort) {
		this.responsePort = responsePort;
	}

	public boolean isPullingFromDb() {
		return isPullingFromDb;
	}

	public void setPullingFromDb(boolean isPullingFromDb) {
		this.isPullingFromDb = isPullingFromDb;
	}

	public int getAckUdpServerPort() {
		return ackUdpServerPort;
	}

	public void setAckUdpServerPort(int ackUdpServerPort) {
		this.ackUdpServerPort = ackUdpServerPort;
	}

}

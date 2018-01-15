package nuance.base;

abstract public class INClientInterface {

	private String xml;
	private String url;
	private String userAgent;
	private String authKey;
	private String originNodeType;
	private String originHostName;
	private String originOperatorID;

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public String getOriginNodeType() {
		return originNodeType;
	}

	public void setOriginNodeType(String originNodeType) {
		this.originNodeType = originNodeType;
	}

	public String getOriginHostName() {
		return originHostName;
	}

	public void setOriginHostName(String originHostName) {
		this.originHostName = originHostName;
	}

	public String getOriginOperatorID() {
		return originOperatorID;
	}

	public void setOriginOperatorID(String originOperatorID) {
		this.originOperatorID = originOperatorID;
	}

	@Override
	public String toString() {
		return "INClientInterface [url=" + url + ", userAgent=" + userAgent + ", authKey=" + authKey
				+ ", originNodeType=" + originNodeType + ", originHostName=" + originHostName + ", originOperatorID="
				+ originOperatorID + "]";
	}

}

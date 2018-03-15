package nuance.base.pojo;

public class Campaign {
	private String campId;
	private String startDate;
	private String endDate;
	private String type;

	public String getCampId() {
		return campId;
	}

	public void setCampId(String campId) {
		this.campId = campId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Campaign [campId=" + campId + ", startDate=" + startDate + ", endDate=" + endDate + ", type=" + type
				+ "]";
	}

}

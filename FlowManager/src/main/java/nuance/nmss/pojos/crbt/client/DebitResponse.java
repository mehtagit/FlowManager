package nuance.nmss.pojos.crbt.client;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DebitResponse {
	@SerializedName("return_description")
	@Expose
	private String returnDescription;
	@SerializedName("return_result")
	@Expose
	private String returnResult;
	@SerializedName("return_message")
	@Expose
	private String returnMessage;
	@SerializedName("return_code")
	@Expose
	private Integer returnCode;

	public String getReturnDescription() {
		return returnDescription;
	}

	public void setReturnDescription(String returnDescription) {
		this.returnDescription = returnDescription;
	}

	public String getReturnResult() {
		return returnResult;
	}

	public void setReturnResult(String returnResult) {
		this.returnResult = returnResult;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public Integer getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(Integer returnCode) {
		this.returnCode = returnCode;
	}

}


package nmss.pojos.crbt.client;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BalanceResponse {

    @SerializedName("return_description")
    @Expose
    private String returnDescription;
    @SerializedName("return_result")
    @Expose
    private List<ReturnResult> returnResult = null;
    @SerializedName("return_message")
    @Expose
    private String returnMessage;
    @SerializedName("return_code")
    @Expose
    private int returnCode;

    public String getReturnDescription() {
        return returnDescription;
    }

    public void setReturnDescription(String returnDescription) {
        this.returnDescription = returnDescription;
    }

    public List<ReturnResult> getReturnResult() {
        return returnResult;
    }

    public void setReturnResult(List<ReturnResult> returnResult) {
        this.returnResult = returnResult;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

}

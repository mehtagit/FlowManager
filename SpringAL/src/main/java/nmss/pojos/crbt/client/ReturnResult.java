
package nmss.pojos.crbt.client;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReturnResult {

    @SerializedName("tx_banks")
    @Expose
    private List<TxBank> txBanks = null;
    @SerializedName("cyclic_banks")
    @Expose
    private List<CyclicBank> cyclicBanks = null;

    public List<TxBank> getTxBanks() {
        return txBanks;
    }

    public void setTxBanks(List<TxBank> txBanks) {
        this.txBanks = txBanks;
    }

    public List<CyclicBank> getCyclicBanks() {
        return cyclicBanks;
    }

    public void setCyclicBanks(List<CyclicBank> cyclicBanks) {
        this.cyclicBanks = cyclicBanks;
    }

}

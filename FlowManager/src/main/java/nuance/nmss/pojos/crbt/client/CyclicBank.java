
package nuance.nmss.pojos.crbt.client;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CyclicBank {

    @SerializedName("last_cycle_open_ind")
    @Expose
    private boolean lastCycleOpenInd;
    @SerializedName("currency_ind")
    @Expose
    private boolean currencyInd;
    @SerializedName("unit_sign")
    @Expose
    private String unitSign;
    @SerializedName("last_balance")
    @Expose
    private Float lastBalance;
    @SerializedName("last_balance_date")
    @Expose
    private String lastBalanceDate;
    @SerializedName("precision")
    @Expose
    private String precision;
    @SerializedName("write_tx_ind")
    @Expose
    private boolean writeTxInd;
    @SerializedName("attribute_obj_num")
    @Expose
    private long attributeObjNum;
    @SerializedName("bank_obj_num")
    @Expose
    private long bankObjNum;
    @SerializedName("subscriber_obj_num")
    @Expose
    private long subscriberObjNum;
    @SerializedName("auto_accumulate_ind")
    @Expose
    private boolean autoAccumulateInd;
    @SerializedName("subscriber_id")
    @Expose
    private String subscriberId;
    @SerializedName("balance")
    @Expose
    private Float balance;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("credit_limit")
    @Expose
    private long creditLimit;
    @SerializedName("exp_date")
    @Expose
    private String expDate;
    @SerializedName("balance_tier_type")
    @Expose
    private long balanceTierType;
    @SerializedName("attribute_name")
    @Expose
    private String attributeName;
    @SerializedName("part_id")
    @Expose
    private String partId;
    @SerializedName("balance_tier_ind")
    @Expose
    private boolean balanceTierInd;
    @SerializedName("next_balance_date")
    @Expose
    private String nextBalanceDate;
    @SerializedName("next_event")
    @Expose
    private long nextEvent;
    @SerializedName("bank_type")
    @Expose
    private long bankType;
    @SerializedName("source_type")
    @Expose
    private String sourceType;
    @SerializedName("OID")
    @Expose
    private String oID;
    @SerializedName("usage_orientation")
    @Expose
    private long usageOrientation;
    @SerializedName("main_bank_ind")
    @Expose
    private boolean mainBankInd;
    @SerializedName("next_event_balance")
    @Expose
    private Float nextEventBalance;
    @SerializedName("srt")
    @Expose
    private String srt;
    @SerializedName("customer_obj_num")
    @Expose
    private long customerObjNum;
    @SerializedName("last_cycle_date")
    @Expose
    private String lastCycleDate;
    @SerializedName("next_cycle_date")
    @Expose
    private String nextCycleDate;

    public boolean isLastCycleOpenInd() {
        return lastCycleOpenInd;
    }

    public void setLastCycleOpenInd(boolean lastCycleOpenInd) {
        this.lastCycleOpenInd = lastCycleOpenInd;
    }

    public boolean isCurrencyInd() {
        return currencyInd;
    }

    public void setCurrencyInd(boolean currencyInd) {
        this.currencyInd = currencyInd;
    }

    public String getUnitSign() {
        return unitSign;
    }

    public void setUnitSign(String unitSign) {
        this.unitSign = unitSign;
    }

    public float getLastBalance() {
        return lastBalance;
    }

    public void setLastBalance(float lastBalance) {
        this.lastBalance = lastBalance;
    }

    public String getLastBalanceDate() {
        return lastBalanceDate;
    }

    public void setLastBalanceDate(String lastBalanceDate) {
        this.lastBalanceDate = lastBalanceDate;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public boolean isWriteTxInd() {
        return writeTxInd;
    }

    public void setWriteTxInd(boolean writeTxInd) {
        this.writeTxInd = writeTxInd;
    }

    public long getAttributeObjNum() {
        return attributeObjNum;
    }

    public void setAttributeObjNum(long attributeObjNum) {
        this.attributeObjNum = attributeObjNum;
    }

    public long getBankObjNum() {
        return bankObjNum;
    }

    public void setBankObjNum(long bankObjNum) {
        this.bankObjNum = bankObjNum;
    }

    public long getSubscriberObjNum() {
        return subscriberObjNum;
    }

    public void setSubscriberObjNum(long subscriberObjNum) {
        this.subscriberObjNum = subscriberObjNum;
    }

    public boolean isAutoAccumulateInd() {
        return autoAccumulateInd;
    }

    public void setAutoAccumulateInd(boolean autoAccumulateInd) {
        this.autoAccumulateInd = autoAccumulateInd;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public long getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(long creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public long getBalanceTierType() {
        return balanceTierType;
    }

    public void setBalanceTierType(long balanceTierType) {
        this.balanceTierType = balanceTierType;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public boolean isBalanceTierInd() {
        return balanceTierInd;
    }

    public void setBalanceTierInd(boolean balanceTierInd) {
        this.balanceTierInd = balanceTierInd;
    }

    public String getNextBalanceDate() {
        return nextBalanceDate;
    }

    public void setNextBalanceDate(String nextBalanceDate) {
        this.nextBalanceDate = nextBalanceDate;
    }

    public long getNextEvent() {
        return nextEvent;
    }

    public void setNextEvent(long nextEvent) {
        this.nextEvent = nextEvent;
    }

    public long getBankType() {
        return bankType;
    }

    public void setBankType(long bankType) {
        this.bankType = bankType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getOID() {
        return oID;
    }

    public void setOID(String oID) {
        this.oID = oID;
    }

    public long getUsageOrientation() {
        return usageOrientation;
    }

    public void setUsageOrientation(long usageOrientation) {
        this.usageOrientation = usageOrientation;
    }

    public boolean isMainBankInd() {
        return mainBankInd;
    }

    public void setMainBankInd(boolean mainBankInd) {
        this.mainBankInd = mainBankInd;
    }

    public Float getNextEventBalance() {
        return nextEventBalance;
    }

    public void setNextEventBalance(Float nextEventBalance) {
        this.nextEventBalance = nextEventBalance;
    }

    public String getSrt() {
        return srt;
    }

    public void setSrt(String srt) {
        this.srt = srt;
    }

    public long getCustomerObjNum() {
        return customerObjNum;
    }

    public void setCustomerObjNum(long customerObjNum) {
        this.customerObjNum = customerObjNum;
    }

    public String getLastCycleDate() {
        return lastCycleDate;
    }

    public void setLastCycleDate(String lastCycleDate) {
        this.lastCycleDate = lastCycleDate;
    }

    public String getNextCycleDate() {
        return nextCycleDate;
    }

    public void setNextCycleDate(String nextCycleDate) {
        this.nextCycleDate = nextCycleDate;
    }

}

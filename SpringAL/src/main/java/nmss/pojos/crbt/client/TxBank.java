
package nmss.pojos.crbt.client;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TxBank {

    @SerializedName("last_cycle_open_ind")
    @Expose
    private boolean lastCycleOpenInd;
    @SerializedName("currency_ind")
    @Expose
    private boolean currencyInd;
    @SerializedName("unit_sign")
    @Expose
    private String unitSign;
    @SerializedName("last_archive_balance")
    @Expose
    private int lastArchiveBalance;
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
    @SerializedName("balance")
    @Expose
    private int balance;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("credit_limit")
    @Expose
    private int creditLimit;
    @SerializedName("balance_tier_type")
    @Expose
    private int balanceTierType;
    @SerializedName("attribute_name")
    @Expose
    private String attributeName;
    @SerializedName("part_id")
    @Expose
    private String partId;
    @SerializedName("balance_tier_ind")
    @Expose
    private boolean balanceTierInd;
    @SerializedName("next_event")
    @Expose
    private int nextEvent;
    @SerializedName("external_tx_balance")
    @Expose
    private int externalTxBalance;
    @SerializedName("last_archive_date")
    @Expose
    private String lastArchiveDate;
    @SerializedName("bank_type")
    @Expose
    private int bankType;
    @SerializedName("invoiced_ind")
    @Expose
    private boolean invoicedInd;
    @SerializedName("source_type")
    @Expose
    private String sourceType;
    @SerializedName("OID")
    @Expose
    private String oID;
    @SerializedName("usage_orientation")
    @Expose
    private int usageOrientation;
    @SerializedName("main_bank_ind")
    @Expose
    private boolean mainBankInd;
    @SerializedName("next_event_balance")
    @Expose
    private int nextEventBalance;
    @SerializedName("customer_obj_num")
    @Expose
    private long customerObjNum;
    @SerializedName("last_cycle_date")
    @Expose
    private String lastCycleDate;
    @SerializedName("next_cycle_date")
    @Expose
    private String nextCycleDate;
    @SerializedName("subscriber_obj_num")
    @Expose
    private long subscriberObjNum;
    @SerializedName("subscriber_id")
    @Expose
    private String subscriberId;

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

    public int getLastArchiveBalance() {
        return lastArchiveBalance;
    }

    public void setLastArchiveBalance(int lastArchiveBalance) {
        this.lastArchiveBalance = lastArchiveBalance;
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

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    public int getBalanceTierType() {
        return balanceTierType;
    }

    public void setBalanceTierType(int balanceTierType) {
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

    public int getNextEvent() {
        return nextEvent;
    }

    public void setNextEvent(int nextEvent) {
        this.nextEvent = nextEvent;
    }

    public int getExternalTxBalance() {
        return externalTxBalance;
    }

    public void setExternalTxBalance(int externalTxBalance) {
        this.externalTxBalance = externalTxBalance;
    }

    public String getLastArchiveDate() {
        return lastArchiveDate;
    }

    public void setLastArchiveDate(String lastArchiveDate) {
        this.lastArchiveDate = lastArchiveDate;
    }

    public int getBankType() {
        return bankType;
    }

    public void setBankType(int bankType) {
        this.bankType = bankType;
    }

    public boolean isInvoicedInd() {
        return invoicedInd;
    }

    public void setInvoicedInd(boolean invoicedInd) {
        this.invoicedInd = invoicedInd;
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

    public int getUsageOrientation() {
        return usageOrientation;
    }

    public void setUsageOrientation(int usageOrientation) {
        this.usageOrientation = usageOrientation;
    }

    public boolean isMainBankInd() {
        return mainBankInd;
    }

    public void setMainBankInd(boolean mainBankInd) {
        this.mainBankInd = mainBankInd;
    }

    public int getNextEventBalance() {
        return nextEventBalance;
    }

    public void setNextEventBalance(int nextEventBalance) {
        this.nextEventBalance = nextEventBalance;
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

    public long getSubscriberObjNum() {
        return subscriberObjNum;
    }

    public void setSubscriberObjNum(long subscriberObjNum) {
        this.subscriberObjNum = subscriberObjNum;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

}

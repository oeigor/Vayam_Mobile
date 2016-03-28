package com.objectedge.store.data.model.order;

import com.objectedge.store.data.model.IBaseModel;
import com.objectedge.store.data.model.LinkModel;

import java.util.List;

/**
 * Created by "Michael Katkov" on 10/7/2015.
 */
public class TransactionResponseModel implements IBaseModel{

    private List<LinkModel> links;
    private String startTime;
    private String transactionId;
    private String errorMessages;
    private boolean resultCode;
    private String endtime;

    public List<LinkModel> getLinks() {
        return links;
    }

    public void setLinks(List<LinkModel> links) {
        this.links = links;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(String errorMessages) {
        this.errorMessages = errorMessages;
    }

    public boolean getResultCode() {
        return resultCode;
    }

    public void setResultCode(boolean resultCode) {
        this.resultCode = resultCode;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}

package com.objectedge.store.data.model.order;

import com.google.gson.Gson;

/**
 * Created by "Michael Katkov" on 10/7/2015.
 */
public class TransactionModel {

    private String transactionAction;
    private TransactionArguments transactionArguments;

    public String getTransactionAction() {
        return transactionAction;
    }

    public void setTransactionAction(String transactionAction) {
        this.transactionAction = transactionAction;
    }

    public TransactionArguments getTransactionArguments() {
        return transactionArguments;
    }

    public void setTransactionArguments(TransactionArguments transactionArguments) {
        this.transactionArguments = transactionArguments;
    }

    public static class TransactionArguments {

        private String orderId;
        private String CCV;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getCCV() {
            return CCV;
        }

        public void setCCV(String CCV) {
            this.CCV = CCV;
        }
    }

    public String buildJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

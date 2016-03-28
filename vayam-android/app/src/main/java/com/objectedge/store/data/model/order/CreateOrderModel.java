package com.objectedge.store.data.model.order;

import com.google.gson.Gson;

/**
 * Created by "Michael Katkov" on 10/6/2015.
 */
public class CreateOrderModel {

    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String buildJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static CreateOrderModel create(String customerId){
        CreateOrderModel createOrderModel = new CreateOrderModel();
        createOrderModel.customerId = customerId;
        return createOrderModel;
    }
}

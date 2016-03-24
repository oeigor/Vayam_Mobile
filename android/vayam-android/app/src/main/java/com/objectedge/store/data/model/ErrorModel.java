package com.objectedge.store.data.model;


/**
 * Created by eloor_000 on 9/9/2015.
 */
public class ErrorModel implements IBaseModel {

    private String code;

    private String message;


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }
}

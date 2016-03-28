package com.objectedge.store.network;


import com.objectedge.store.data.parser.IBaseParser;
import com.squareup.okhttp.Request;

public class RequestModel<T> {

    private Request request;
    private IBaseParser parser;
    private Callback<T> callback;

    public RequestModel(){

    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public IBaseParser getParser() {
        return parser;
    }

    public void setParser(IBaseParser parser) {
        this.parser = parser;
    }

    public Callback<T> getCallback() {
        return callback;
    }

    public void setCallback(Callback<T> callback) {
        this.callback = callback;
    }
}

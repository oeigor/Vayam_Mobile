package com.objectedge.store.data.parser;

import com.objectedge.store.data.model.IBaseModel;

import org.json.JSONException;

public interface IBaseParser {

    public IBaseModel parse(String string) throws JSONException;

}

package com.objectedge.store.data.parser;

import com.google.gson.Gson;
import com.objectedge.store.data.model.ErrorModel;

import com.objectedge.store.data.model.IBaseModel;
import com.objectedge.store.data.model.order.OrderModel;
import com.objectedge.store.utils.ConstantsLoader;
import com.objectedge.store.utils.WLog;
import org.json.JSONException;

/**
 * Created by eloor_000 on 9/9/2015.
 */
public class ErrorParser implements IBaseParser{

    private static final String TAG = "ErrorParser";

    @Override
    public IBaseModel parse(String string) throws JSONException {
        Gson gson = new Gson();
        ErrorModel errorModel;
        try {
            errorModel = gson.fromJson(string, ErrorModel.class);
        } catch (Exception e){
            WLog.d(TAG, e.getMessage());
            throw new JSONException(ConstantsLoader.PARSER_EXCEPTION);
        }
        return errorModel;
    }

}

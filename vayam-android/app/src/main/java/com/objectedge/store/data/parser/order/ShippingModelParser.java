package com.objectedge.store.data.parser.order;

import com.google.gson.Gson;
import com.objectedge.store.data.model.IBaseModel;
import com.objectedge.store.data.model.order.OrderModel;
import com.objectedge.store.data.parser.IBaseParser;
import com.objectedge.store.utils.ConstantsLoader;
import com.objectedge.store.utils.WLog;

import org.json.JSONException;

/**
 * Created by "Michael Katkov" on 10/6/2015.
 */
public class ShippingModelParser implements IBaseParser{

    private static final String TAG = "ShippingModelParser";

    @Override
    public IBaseModel parse(String string) throws JSONException {
        Gson gson = new Gson();
        OrderModel.ShippingGroupModel shippingGroupModel;
        try {
            shippingGroupModel = gson.fromJson(string, OrderModel.ShippingGroupModel.class);
        } catch (Exception e){
            WLog.d(TAG, e.getMessage());
            throw new JSONException(ConstantsLoader.PARSER_EXCEPTION);
        }
        return shippingGroupModel;
    }
}

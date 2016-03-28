package com.objectedge.store.data.parser.product;

import com.google.gson.Gson;
import com.objectedge.store.data.model.IBaseModel;
import com.objectedge.store.data.model.product.ProductModel;
import com.objectedge.store.data.parser.IBaseParser;
import com.objectedge.store.utils.ConstantsLoader;
import com.objectedge.store.utils.WLog;

import org.json.JSONException;

/**
 * Created by "Michael Katkov" on 9/11/2015.
 */
public class ProductParser implements IBaseParser {

    private static final String TAG = "ProductParser";

    @Override
    public IBaseModel parse(String string) throws JSONException {
        Gson gson = new Gson();
        ProductModel productModel;
        try {
            productModel = gson.fromJson(string, ProductModel.class);
        } catch (Exception e){
            WLog.d(TAG, e.getMessage());
            throw new JSONException(ConstantsLoader.PARSER_EXCEPTION);
        }
        return productModel;
    }

}

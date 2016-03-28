package com.objectedge.store.data.parser.skus;


import com.google.gson.Gson;
import com.objectedge.store.data.model.IBaseModel;
import com.objectedge.store.data.model.sku.SkuModelList;
import com.objectedge.store.data.parser.IBaseParser;
import com.objectedge.store.utils.ConstantsLoader;
import com.objectedge.store.utils.WLog;
import org.json.JSONException;


/**
 * Created by "Michael Katkov" on 10/8/2015.
 */
public class SkusListParser implements IBaseParser {

    private static final String TAG = "SkusListParser";

    @Override
    public IBaseModel parse(String string) throws JSONException {
        Gson gson = new Gson();
        SkuModelList skuModelList;
        try {
            skuModelList = gson.fromJson(string, SkuModelList.class);
        } catch (Exception e){
            WLog.d(TAG, e.getMessage());
            throw new JSONException(ConstantsLoader.PARSER_EXCEPTION);
        }
        return skuModelList;
    }

}

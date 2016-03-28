package com.objectedge.store.data.parser.user;

import com.google.gson.Gson;
import com.objectedge.store.data.model.IBaseModel;
import com.objectedge.store.data.model.user.UserModel;
import com.objectedge.store.data.parser.IBaseParser;
import com.objectedge.store.utils.ConstantsLoader;
import com.objectedge.store.utils.WLog;

import org.json.JSONException;

/**
 * Created by eloor_000 on 9/9/2015.
 */
public class UserParser implements IBaseParser{

    private static final String TAG = "UserParser";

    @Override
    public IBaseModel parse(String string) throws JSONException {
        Gson gson = new Gson();
        UserModel userModel;
        try {
            userModel = gson.fromJson(string, UserModel.class);
        } catch (Exception e){
            WLog.d(TAG, e.getMessage());
            throw new JSONException(ConstantsLoader.PARSER_EXCEPTION);
        }
        return userModel;
    }

}

package com.objectedge.store.data.parser.user;

import com.google.gson.Gson;
import com.objectedge.store.data.model.IBaseModel;
import com.objectedge.store.data.model.user.UsersListModel;
import com.objectedge.store.data.parser.IBaseParser;
import com.objectedge.store.utils.ConstantsLoader;
import com.objectedge.store.utils.WLog;

import org.json.JSONException;

/**
 * Created by "Michael Katkov" on 9/10/2015.
 */
public class UsersListParser implements IBaseParser{

    private static final String TAG = "UsersListParser";

    @Override
    public IBaseModel parse(String string) throws JSONException {
        Gson gson = new Gson();
        UsersListModel usersListModel;
        try {
            usersListModel = gson.fromJson(string, UsersListModel.class);
        } catch (Exception e){
            WLog.d(TAG, e.getMessage());
            throw new JSONException(ConstantsLoader.PARSER_EXCEPTION);
        }
        return usersListModel;
    }

}

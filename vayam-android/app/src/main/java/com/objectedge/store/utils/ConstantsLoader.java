package com.objectedge.store.utils;

import android.content.Context;

import com.objectedge.store.R;

/**
 * Created by eloor_000 on 9/9/2015.
 */
public class ConstantsLoader {

    public static String UKNOWN_EXCEPTION;
    public static String SERVER_EXCEPTION;
    public static String PARSER_EXCEPTION;

    public static void init(Context context){
        UKNOWN_EXCEPTION = context.getResources().getString(R.string.unknown_error);
        SERVER_EXCEPTION = context.getResources().getString(R.string.server_error);
        PARSER_EXCEPTION = context.getResources().getString(R.string.parser_error);
    }
}

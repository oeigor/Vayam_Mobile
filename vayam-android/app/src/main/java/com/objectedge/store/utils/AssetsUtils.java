package com.objectedge.store.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by eloor_000 on 4/26/2015.
 */
public class AssetsUtils {

    public static final String TAG = "AssetsUtils";

    public static String readFile(Context context, String filename){
        String resultString = "";
        AssetManager am = context.getAssets();
        try {
            InputStream is = am.open(filename);
            resultString = StringUtils.readStringFromInput(is);
        } catch (IOException e) {
            WLog.d(TAG, e.getMessage());
        }
        return resultString;
    }
}

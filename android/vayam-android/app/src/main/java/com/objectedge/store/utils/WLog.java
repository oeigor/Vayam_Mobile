package com.objectedge.store.utils;

import android.util.Log;


public class WLog {

    private static boolean debug = true;

    public static void d(String TAG, String message){
        if(debug) {
            Log.d(TAG, message);
        }
    }

    public static void i(String TAG, String message){
        if(debug) {
            Log.i(TAG, message);
        }
    }
}

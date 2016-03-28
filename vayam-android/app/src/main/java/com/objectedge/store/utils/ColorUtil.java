package com.objectedge.store.utils;

import android.content.Context;
import android.os.Build;

/**
 * Created by "Michael Katkov" on 9/30/2015.
 */
public class ColorUtil {

    public static int getColor(Context context, int source) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return context.getResources().getColor(source, null);
        }else{
            return context.getResources().getColor(source);
        }
    }
}

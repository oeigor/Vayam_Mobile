package com.objectedge.store.utils;

import android.content.Context;
import android.content.res.Configuration;

import com.objectedge.store.R;

/**
 * Created by eloor_000 on 4/6/2015.
 */
public class LayoutUtils {

    public static int getProductsGridColumnCount(Context context){
        boolean isTablet = context.getResources().getBoolean(R.bool.is_tablet);
        if(isTablet){
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                return 4;
            } else {
                return 6;
            }
        }else {
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                return 2;
            } else {
                return 3;
            }
        }
    }

    public static int getFavoritesGridColumnCount(Context context){
        boolean isTablet = context.getResources().getBoolean(R.bool.is_tablet);
        if(isTablet){
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                return 2;
            } else {
                return 3;
            }
        }else {
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                return 1;
            } else {
                return 2;
            }
        }
    }
}

package com.objectedge.store.utils;


import android.content.Context;
import android.widget.EditText;
import com.objectedge.store.R;


/**
 * Created by eloor_000 on 6/4/2015.
 */
public class ViewUtils {

    public static void setErrorBackground(Context context, EditText editText){
        editText.setBackgroundColor(ColorUtil.getColor(context, R.color.pink));
        //editText.setHint(R.string.this_field_is_mandatory);
        //editText.setHintTextColor(ColorUtil.getColor(context, R.color.red));
    }

    public static void setNormalBackground(Context context, EditText editText){
        editText.setBackgroundColor(ColorUtil.getColor(context, R.color.white));
        //editText.setHint("");
        //editText.setHintTextColor(ColorUtil.getColor(context, R.color.blue));
    }

}

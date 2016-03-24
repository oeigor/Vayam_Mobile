package com.objectedge.store.data.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.objectedge.store.R;


/**
 * Created by eloor_000 on 4/29/2015.
 */
public class PreferenceManager {

    private SharedPreferences preferences;

    public PreferenceManager(Context context){
        preferences = context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public void putInt(String name, int value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(name, value);
        editor.commit();
    }

    public int getInt(String name){
        return preferences.getInt(name,-1);
    }

    public void putString(String name, String value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(name, value);
        editor.commit();
    }

    public void putStringInBackground(String name, String value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(name, value);
        editor.apply();
    }

    public String getString(String name){
        return preferences.getString(name, null);
    }

    public void clearData(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().commit();
    }
}

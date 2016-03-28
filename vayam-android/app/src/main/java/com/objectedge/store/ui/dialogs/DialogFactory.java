package com.objectedge.store.ui.dialogs;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.objectedge.store.StoreApplication;


/**
 * Created by eloor_000 on 5/21/2015.
 */
public class DialogFactory {

    private static FragmentTransaction initFragmentTransaction(AppCompatActivity activity){
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        Fragment prev = activity.getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        return ft;
    }

    public static void showDialog(AppCompatActivity activity, DialogFragment dialog){
        if(StoreApplication.isActivityVisible()) {
            FragmentTransaction ft = initFragmentTransaction(activity);
            dialog.show(ft, "dialog");
        }
    }

    public static void hideDialog(AppCompatActivity activity){
        DialogFragment dialogFragment = ((DialogFragment) activity.getSupportFragmentManager().
                findFragmentByTag("dialog"));
        if(dialogFragment != null) {
            if(dialogFragment.getDialog() != null) {
                dialogFragment.getDialog().dismiss();
            }
        }
    }

    public static boolean isExistDialog(AppCompatActivity activity){
        boolean result = false;
        DialogFragment dialogFragment = ((DialogFragment) activity.getSupportFragmentManager().
                findFragmentByTag("dialog"));
        if(dialogFragment != null) {
            result = true;
        }
        return result;
    }
}

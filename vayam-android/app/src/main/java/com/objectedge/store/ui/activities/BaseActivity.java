package com.objectedge.store.ui.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;

import android.support.v7.app.AppCompatActivity;

import com.objectedge.store.StoreApplication;
import com.objectedge.store.ui.dialogs.DialogFactory;
import com.objectedge.store.ui.dialogs.OkDialog;
import com.objectedge.store.ui.dialogs.ProgressDialogFragment;
import com.objectedge.store.utils.LocalMessenger;


/**
 * Created by eloor_000 on 6/5/2015.
 */
public class BaseActivity extends AppCompatActivity {

    public final static String TAG = "BaseActivity";
    public final static String SHOW_PROGRESS = "show_progress";
    public final static String SHOW_ALERT = "show_alert";
    public final static String SHOW_NO_INTERNET = "show_no_internet";

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    private BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(TAG)) {
                //show alert
                String alertMessage = intent.getStringExtra(SHOW_ALERT);
                if(alertMessage != null){
                    showAlertDialog(alertMessage);
                }
                //check progress
                if(intent.getBooleanExtra(SHOW_PROGRESS, false)){
                    showProgress();
                } else {
                    hideProgress();
                }

                if(intent.getBooleanExtra(SHOW_NO_INTERNET, false)){
                    showNoInternetDialog();
                }
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        LocalMessenger.registerReceiver(this, TAG, messageReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        StoreApplication.activityResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        StoreApplication.activityPaused();
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalMessenger.unRegisterReceiver(this, messageReceiver);
    }

    public void showProgress(){
        ProgressDialogFragment progressFragment = ProgressDialogFragment.newInstance();
        DialogFactory.showDialog(this, progressFragment);
    }

    public void hideProgress(){
        DialogFactory.hideDialog(this);
    }

    private void showAlertDialog(final String message){
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                OkDialog okDialog = OkDialog.newInstance(message);
                DialogFactory.showDialog(BaseActivity.this, okDialog);
            }
        }, 200);
    }

    private void showNoInternetDialog(){
        OkDialog okDialog = OkDialog.newInstance("No internet connection. SurfStitch application can't work without internet");
        okDialog.setOnDialogButtonsClickListener(new OkDialog.OnDialogButtonsClickListener() {
            @Override
            public void onOK() {

            }
        });
        DialogFactory.showDialog(BaseActivity.this, okDialog);
    }

}

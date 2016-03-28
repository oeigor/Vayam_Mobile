package com.objectedge.store.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.objectedge.store.R;
import com.objectedge.store.StoreApplication;
import com.objectedge.store.data.model.fake.CatalogModel;
import com.objectedge.store.network.Callback;
import com.objectedge.store.network.ResponseModel;
import com.objectedge.store.ui.dialogs.DialogFactory;
import com.objectedge.store.ui.dialogs.YesNoDialog;


public class LaunchActivity extends BaseActivity {

    private ProgressBar progressBar;
    private StoreApplication storeApplication;

    public static void start(Context context){
        Intent startLaunchActivityIntent = new Intent(context, LaunchActivity.class);
        context.startActivity(startLaunchActivityIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        progressBar = (ProgressBar) findViewById(R.id.launchProgress);
        storeApplication = ((StoreApplication)getApplication());
        checkIsEnoughDataToStart();
    }

    private void checkIsEnoughDataToStart(){
        //check if is categoriesList is full
        if(storeApplication.getCategoriesManager().getCategoriesList().isEmpty()) {
            storeApplication.getNetworkManager().getCatalog(catalogCallback);
        } else{
            LoginActivity.start(LaunchActivity.this);
            finish();
        }
    }

    private Callback<ResponseModel<CatalogModel>> catalogCallback = new Callback<ResponseModel<CatalogModel>>() {

        private int tries = 0;
        private static final int ATTEMPTS = 3;

        @Override
        public void onLoadStarted() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onLoadFinished(ResponseModel<CatalogModel> response) {
            /*
            if(response.exception != null){
                if(tries >= ATTEMPTS){
                    showAlarmDialog();
                } else {
                    tries++;
                    storeApplication.getNetworkManager().getCatalog(catalogCallback);
                }
            } else {
                storeApplication.getCategoriesManager().setCategoriesList(response.model.getCategoryModelList());
                LoginActivity.start(LaunchActivity.this);
                finish();
            }
            */
            LoginActivity.start(LaunchActivity.this);
            finish();
        }
    };

    private void showAlarmDialog(){
        YesNoDialog alarmDialogFragment = YesNoDialog.newInstance(getString(R.string.server_not_respond));
        alarmDialogFragment.setOnDialogButtonsClickListener(new YesNoDialog.OnDialogButtonsClickListener() {
            @Override
            public void onYes() {
                storeApplication.getNetworkManager().getCatalog(catalogCallback);
            }

            @Override
            public void onNo() {
                finish();
            }
        });
        DialogFactory.showDialog(LaunchActivity.this, alarmDialogFragment);
    }
}

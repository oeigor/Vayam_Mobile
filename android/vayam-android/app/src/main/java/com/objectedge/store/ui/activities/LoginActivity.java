package com.objectedge.store.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.objectedge.store.R;
import com.objectedge.store.StoreApplication;
import com.objectedge.store.data.manager.ModelObserver;
import com.objectedge.store.data.manager.NetworkManager;
import com.objectedge.store.data.manager.UsersManager;
import com.objectedge.store.data.model.user.UserModel;
import com.objectedge.store.ui.dialogs.DialogFactory;
import com.objectedge.store.ui.dialogs.YesNoDialog;


public class LoginActivity extends BaseActivity implements ModelObserver{

    private Button signInButton;
    private Button shopAsQuestButton;
    private ProgressBar signProgress;
    private NetworkManager networkManager;
    private UsersManager usersManager;
    //private AccessManager profileManager;

    public static void start(Context context){
        Intent startLoginActivityIntent = new Intent(context, LoginActivity.class);
        context.startActivity(startLoginActivityIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //init Manager
        networkManager = ((StoreApplication)getApplication()).getNetworkManager();
        usersManager = ((StoreApplication)getApplication()).getUsersManager();
        //check if we have already profile
        if(usersManager.getCurrentUser() != null){
            MainActivity.start(this, true);
            finish();
        }
        //check if we have already real profile
        if(usersManager.backUpUserModel() != null){
            MainActivity.start(this, true);
            finish();
        }
        //init Views
        signInButton = (Button)findViewById(R.id.signInButton);
        shopAsQuestButton = (Button)findViewById(R.id.shopAsQuestButton);
        signProgress = (ProgressBar)findViewById(R.id.signProgress);
        signInButton.setOnClickListener(onSignInClick);
        shopAsQuestButton.setOnClickListener(onShopAsQuestClick);

    }


    private View.OnClickListener onSignInClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LoginRegisterActivity.start(LoginActivity.this, LoginRegisterActivity.START_ACTIVITY_AFTER);
        }
    };

    private View.OnClickListener onShopAsQuestClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(usersManager.getCurrentUser() == null) {
                usersManager.addUser(usersManager.createFakeUser());
                signProgress.setVisibility(View.VISIBLE);
                signInButton.setVisibility(View.GONE);
                shopAsQuestButton.setVisibility(View.GONE);
            } else {
                MainActivity.start(LoginActivity.this, true);
                finish();
            }
        }
    };


    @Override
    public void modelChanged(String message, String error) {
        if (error == null) {
            MainActivity.start(LoginActivity.this, true);
            finish();
        } else {
            showAlarmDialog();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        usersManager.addObserver(this);
    }


    @Override
    protected void onStop() {
        super.onStop();
        usersManager.removeObserver(this);
    }


    private void showAlarmDialog() {
        YesNoDialog alarmDialogFragment = YesNoDialog
                .newInstance("Server is unavailable now. Do you want to try one more time?");
        alarmDialogFragment.setOnDialogButtonsClickListener(new YesNoDialog.OnDialogButtonsClickListener() {

            @Override
            public void onYes() {
                usersManager.addUser(usersManager.createFakeUser());
            }


            @Override
            public void onNo() {
                finish();
            }
        });
        DialogFactory.showDialog(LoginActivity.this, alarmDialogFragment);
    }

}

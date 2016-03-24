package com.objectedge.store.data.manager;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.objectedge.store.StoreApplication;
import com.objectedge.store.data.model.user.AddUserModel;
import com.objectedge.store.data.model.user.UserModel;
import com.objectedge.store.network.Callback;
import com.objectedge.store.network.ResponseModel;
import com.objectedge.store.ui.activities.BaseActivity;
import com.objectedge.store.utils.LocalMessenger;
import com.objectedge.store.utils.RegisterValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eloor_000 on 4/29/2015.
 */
public class UsersManager implements ModelObservable{

    public static final String TAG = "UserManager";

    private Context context;
    private UserModel currentUser;
    private List<ModelObserver> observerList;

    public UsersManager(Context context) {
        this.context = context;
        observerList = new ArrayList<>();
    }

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public void setCurentUser(UserModel userModel) {
        this.currentUser = userModel;
        storeCurrentUser(userModel);
    }

    public void clearData() {
        currentUser = null;
    }

    public void storeCurrentUser(UserModel userModel) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(userModel);
        ((StoreApplication) context.getApplicationContext()).getPreferenceManager().putString(TAG, jsonString);
    }

    public UserModel backUpUserModel() {
        Gson gson = new Gson();
        String jsonString = ((StoreApplication) context.getApplicationContext()).getPreferenceManager().getString(TAG);
        UserModel model = gson.fromJson(jsonString, UserModel.class);
        currentUser = model;
        return model;
    }

    @Override
    public void addObserver(ModelObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(ModelObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void notifyObservers(String message, String error) {
        for (ModelObserver observer: observerList){
            observer.modelChanged(message, error);
        }
    }

    public void addUser(AddUserModel addUserModel){
        final NetworkManager networkManager = ((StoreApplication)context.getApplicationContext()).getNetworkManager();
        networkManager.addUser(addUserModel, new Callback<ResponseModel<UserModel>>() {
            @Override
            public void onLoadStarted() {
                //LocalMessenger.sendMessage(context, BaseActivity.TAG, BaseActivity.SHOW_PROGRESS, true);
            }

            @Override
            public void onLoadFinished(ResponseModel<UserModel> responseModel) {
                //LocalMessenger.sendMessage(context, BaseActivity.TAG, BaseActivity.SHOW_PROGRESS, false);
                if(responseModel.exception != null) {
                    notifyObservers(TAG, responseModel.exception.getMessage());
                } else {
                    setCurentUser(responseModel.model);
                    notifyObservers(TAG, null);
                }
            }
        });
    }

    public AddUserModel createFakeUser() {
        AddUserModel addUserModel = new AddUserModel();
        addUserModel.addName(new AddUserModel.Name("Ram","givenName"));
        addUserModel.addName(new AddUserModel.Name("Subramanian", "familyName"));
        addUserModel.setEmail("sram@objectedge.com");
        addUserModel.setPassword("1234");
        addUserModel.setUserId(System.currentTimeMillis() + "r");
        return addUserModel;
    }
}

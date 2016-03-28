package com.objectedge.store.network.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.objectedge.store.StoreApplication;
import com.objectedge.store.data.manager.OrdersManager;
import com.objectedge.store.data.manager.UsersManager;
import com.objectedge.store.data.model.order.CreateOrderModel;

public class OrdersLoaderService extends Service {

    public final static String TAG = "OrdersLoaderService";
    private OrdersManager ordersManager;
    private UsersManager userManager;

    public OrdersLoaderService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        userManager = ((StoreApplication)getApplication()).getUsersManager();
        ordersManager = ((StoreApplication)getApplication()).getOrdersManager();
        if(userManager.getCurrentUser() != null) {
            ordersManager.createOrder(CreateOrderModel.create(userManager.getCurrentUser().getUserId()));
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}

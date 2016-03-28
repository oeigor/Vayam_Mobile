package com.objectedge.store.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by eloor_000 on 4/16/2015.
 */
public class LocalMessenger {

    /**
     * Send message to receivers
     * @param context context
     * @param action action of the message
     * @param extra extra of the message
     * @param message message
     */
    public static void sendMessage(Context context, String action, String extra, String message){
        Intent intent = new Intent(action);
        intent.putExtra(extra,message);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    /**
     * Send message to receivers
     * @param context context
     * @param action action of the message
     * @param extra extra of the message
     * @param message message
     */
    public static void sendMessage(Context context, String action, String extra, boolean message){
        Intent intent = new Intent(action);
        intent.putExtra(extra,message);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    /**
     * Send message to receivers
     * @param context context
     * @param extra action of the message
     * @param message message
     */
//    public static void sendRefineViewMessage(Context context, String extra, String message){
//        sendMessage(context, RefineView.TAG, extra, message);
//    }

    /**
     * Register receiver for local messages
     * @param context can be Activity.Context or Application.Context
     * @param action type of action that will catch the receiver
     * @param receiver class that will catch messages
     */
    public static void registerReceiver(Context context, String action, BroadcastReceiver receiver){
        IntentFilter intentFilter = new IntentFilter(action);
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, intentFilter);
    }

    /**
     * UnRegister receiver
     * @param context can be can be Activity.Context or Application.Context
     * @param receiver class that will be dismissed from the messages of LocalMessenger
     */
    public static void unRegisterReceiver(Context context, BroadcastReceiver receiver){
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
    }
}

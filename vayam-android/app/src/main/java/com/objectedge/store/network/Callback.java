package com.objectedge.store.network;

/**
 * Created by eloor_000 on 8/6/2014.
 */
public interface Callback<T> {
    public void onLoadStarted();
    public void onLoadFinished(T model);
}

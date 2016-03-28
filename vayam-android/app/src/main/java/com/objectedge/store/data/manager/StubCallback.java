package com.objectedge.store.data.manager;

import com.objectedge.store.network.Callback;

/**
 * Created by "Michael Katkov" on 9/15/2015.
 */
public class StubCallback<T> implements Callback<T> {

    @Override
    public void onLoadStarted() {

    }

    @Override
    public void onLoadFinished(T model) {

    }
}

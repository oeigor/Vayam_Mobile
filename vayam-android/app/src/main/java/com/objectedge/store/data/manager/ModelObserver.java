package com.objectedge.store.data.manager;

/**
 * Created by eloor_000 on 4/6/2015.
 */
public interface ModelObserver {
    public void modelChanged(String message, String error);
}

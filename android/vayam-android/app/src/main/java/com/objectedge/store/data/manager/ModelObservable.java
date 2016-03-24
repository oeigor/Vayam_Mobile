package com.objectedge.store.data.manager;

/**
 * Created by eloor_000 on 4/6/2015.
 */
public interface ModelObservable {

    public void addObserver(ModelObserver observer);

    public void removeObserver(ModelObserver observer);

    public void notifyObservers(String message, String error);

}

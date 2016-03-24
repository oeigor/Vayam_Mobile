package com.objectedge.store;

import android.app.Application;
import android.content.Intent;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import com.objectedge.store.data.manager.CategoriesManager;
import com.objectedge.store.data.manager.NetworkManager;
import com.objectedge.store.data.manager.OrdersManager;
import com.objectedge.store.data.manager.PreferenceManager;
import com.objectedge.store.data.manager.ProductsManager;
import com.objectedge.store.data.manager.FavoritesManager;
import com.objectedge.store.data.manager.UsersManager;
import com.objectedge.store.network.services.OrdersLoaderService;
import com.objectedge.store.utils.ConstantsLoader;

/**
 * Created by eloor_000 on 9/9/2015.
 */
public class StoreApplication extends Application{

    private static boolean activityVisible;
    private NetworkManager networkManager;
    private CategoriesManager categoriesManager;
    private ProductsManager productsManager;
    private PreferenceManager preferenceManager;
    private FavoritesManager favoritesManager;
    private UsersManager usersManager;
    private OrdersManager ordersManager;

    @Override
    public void onCreate() {
        super.onCreate();
        ConstantsLoader.init(this);
        initImageLoader();
        getFavoritesManager();
    }

    public NetworkManager getNetworkManager(){
        if(networkManager == null){
            networkManager = new NetworkManager(this);
        }
        return networkManager;
    }

    public CategoriesManager getCategoriesManager(){
        if(categoriesManager == null){
            categoriesManager = new CategoriesManager(this);
        }
        return categoriesManager;
    }

    public ProductsManager getProductsManager(){
        if(productsManager == null){
            productsManager = new ProductsManager(this);
        }
        return productsManager;
    }

    public PreferenceManager getPreferenceManager(){
        if(preferenceManager == null){
            preferenceManager = new PreferenceManager(this);
        }
        return preferenceManager;
    }

    public FavoritesManager getFavoritesManager(){
        if(favoritesManager == null){
            favoritesManager = new FavoritesManager(this);
        }
        return favoritesManager;
    }

    public UsersManager getUsersManager() {
        if(usersManager == null){
            usersManager = new UsersManager(this);
        }
        return usersManager;
    }

    public OrdersManager getOrdersManager() {
        if(ordersManager == null){
            ordersManager = new OrdersManager(this);
        }
        return ordersManager;
    }

    private void initImageLoader(){
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

    public void startOrderLoaderService(){
        Intent ordersIntent = new Intent(this, OrdersLoaderService.class);
        startService(ordersIntent);
    }

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }


}

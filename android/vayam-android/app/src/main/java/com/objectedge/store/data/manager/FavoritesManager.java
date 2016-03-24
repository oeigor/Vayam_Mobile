package com.objectedge.store.data.manager;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.objectedge.store.StoreApplication;
import com.objectedge.store.data.model.product.ProductModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eloor_000 on 5/1/2015.
 */
public class FavoritesManager implements ModelObservable{

    public static final String TAG = "FavoritesManager";

    private Context context;
    private List<ProductModel> favorites;
    private List<ModelObserver> observerList;


    public FavoritesManager(Context context){
        this.context = context;
        favorites = restoreModel();
        observerList = new ArrayList<>();
    }

    public List<ProductModel> getFavorites() {
        return favorites;
    }

    public void clearData(){
        observerList = new ArrayList<>();
        favorites = null;
    }

    public void setFavorites(List<ProductModel> favorites) {
        this.favorites = favorites;
        //backUp sizes from preferences
        //backUpSizes();
        notifyObservers(TAG, null);
    }

    private List<ProductModel> restoreModel(){
        List<ProductModel> resultList = new ArrayList<>();
        PreferenceManager preferenceManager = ((StoreApplication)context.getApplicationContext()).getPreferenceManager();
        String jsonString = preferenceManager.getString(TAG);
        if(jsonString != null) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<ProductModel>>(){}.getType();
            resultList = gson.fromJson(jsonString,listType);
        }
        return resultList;
    }

    private void saveModel(){
        PreferenceManager preferenceManager = ((StoreApplication)context.getApplicationContext()).getPreferenceManager();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ProductModel>>(){}.getType();
        String jsonString = gson.toJson(favorites, listType);
        preferenceManager.putString(TAG,jsonString);
    }

    public void addToFavorites(ProductModel productModel){
        favorites.add(productModel);
        saveModel();
        notifyObservers(TAG, null);
    }

    public void removeFromFavorites(ProductModel curProduct) {
        favorites.remove(curProduct);
        saveModel();
        notifyObservers(TAG, null);
    }

    public boolean contains(ProductModel productModel){
        for(ProductModel model: favorites){
            if(productModel.getItemID().equals(model.getItemID())){
                return true;
            }
        }
        return false;
    }
    /*
    private void updateFavorites(WishListModel favorites){
        if(this.favorites.getTotal_quantity() != favorites.getTotal_quantity()) {
            this.favorites.setTotal_quantity(favorites.getTotal_quantity());
            this.favorites.getList_items().clear();
            this.favorites.getList_items().addAll(favorites.getList_items());
            //backUp sizes from preferences
            backUpSizes();
            notifyObservers();
        }
    }


    public void addToWishList(String product_id, String sku_id){
        if(favorites != null) {
            WLog.d(TAG, "Add to wishList wishListId: " + favorites.getWishlist_id()
                        + " productId: " + product_id + " skuId: " + sku_id);
            AddToFavoritesModel addToFavoritesModel = new AddToFavoritesModel();
            addToFavoritesModel.fillByProductIdAndSizeId(product_id, sku_id);
            NetworkManager networkManager =
                    ((SurfStitchApplication)context.getApplicationContext()).getNetworkManager();
            networkManager.addToWishList(favorites.getWishlist_id(),addToFavoritesModel,
                                                                         addCallback);
        }
    }

    public void removeFromWishList(WishlistItemModel wishListItem){
        if(favorites != null){
            WLog.d(TAG, "Remove from wishList wishListId: " + favorites.getWishlist_id()
                        + " productId: " + wishListItem.getProduct_id()
                        + " skuId: " + wishListItem.getAttributes().getSizes().get(0).getSku_id()
                        + " wishListItemId: " + wishListItem.getWishlist_item_id());
            NetworkManager networkManager =
                    ((SurfStitchApplication)context.getApplicationContext()).getNetworkManager();
            networkManager.removeFromWishList(favorites.getWishlist_id(),
                                 wishListItem.getWishlist_item_id(), updateRemoveCallback);
        }
    }

    public void removeFromWishList(String productId){
        removeFromWishList(getWishListItemByProductId(productId));
    }

    private void backUpSizes(){
        PreferenceManager preferenceManager = ((SurfStitchApplication)context.
                getApplicationContext()).getPreferenceManager();
        for(WishlistItemModel model: favorites.getList_items()) {
            String skuId = preferenceManager.getString(model.getProduct_id());
            if (skuId != null) {
                model.getAttributes().getSizes().get(0).setSku_id(skuId);
            }
        }
    }

    public void changeSize(String product_id, String sku_id){
        //it is obnoxious using
        //removeFromWishList(product_id);
        //addToWishList(product_id,sku_id);
        //We will use preference to store sizes, because it is impossible to modify
        //wishItems on server side
        PreferenceManager preferenceManager = ((SurfStitchApplication)
                                        context.getApplicationContext()).getPreferenceManager();
        preferenceManager.putString(product_id,sku_id);
        getWishListItemByProductId(product_id).getAttributes().getSizes().get(0).setSku_id(sku_id);
    }

    public void updateWishList(){
        NetworkManager networkManager =
                ((SurfStitchApplication)context.getApplicationContext()).getNetworkManager();
        networkManager.getWishList(updateRemoveCallback);
    }

    public WishlistItemModel getWishListItemByProductId(String productId){
        WishlistItemModel result = null;
        if(favorites != null && favorites.getList_items() != null){
            for(WishlistItemModel wishlistItemModel : favorites.getList_items()){
                if(wishlistItemModel.getProduct_id().equals(productId)){
                    result = wishlistItemModel;
                }
            }
        }

        return result;
    }

    private Callback<ResponseModel<WishListModel>> updateRemoveCallback = new Callback<ResponseModel<WishListModel>>() {

        @Override
        public void onLoadStarted() {
            LocalMessenger.sendMessage(context, MainActivity.TAG, MainActivity.SHOW_PROGRESS, true);
        }

        @Override
        public void onLoadFinished(ResponseModel<WishListModel> response) {
            LocalMessenger.sendMessage(context, MainActivity.TAG, MainActivity.SHOW_PROGRESS, false);
            if(response.exception != null){
                WLog.d(TAG, "WishList isn't able to load");
                Toast.makeText(context, response.exception.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                updateFavorites(response.model);
            }
        }
    };

    private Callback<ResponseModel<WishListWithPagingModel>> addCallback = new Callback<ResponseModel<WishListWithPagingModel>>() {

        @Override
        public void onLoadStarted() {
            //LocalMessenger.sendMessage(context, MainActivity.TAG, MainActivity.SHOW_PROGRESS, true);
        }

        @Override
        public void onLoadFinished(ResponseModel<WishListWithPagingModel> response) {
            //LocalMessenger.sendMessage(context, MainActivity.TAG, MainActivity.SHOW_PROGRESS, false);
            if(response.exception != null){
                WLog.d(TAG, "WishList isn't able to load");
                Toast.makeText(context, response.exception.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                updateFavorites(response.model.getWishList());
            }
        }
    };
    */
    @Override
    public void addObserver(ModelObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(ModelObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers(String message, String error) {
        for(ModelObserver observer: observerList){
            observer.modelChanged(message,error);
        }
    }


}

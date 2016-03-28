package com.objectedge.store.data.manager;


import android.content.Context;
import android.widget.Toast;
import com.objectedge.store.StoreApplication;
import com.objectedge.store.data.model.product.ProductModel;
import com.objectedge.store.data.model.product.ProductsListModel;
import com.objectedge.store.data.model.sku.SkuModel;
import com.objectedge.store.data.model.sku.SkuModelList;
import com.objectedge.store.network.Callback;
import com.objectedge.store.network.ResponseModel;
import com.objectedge.store.ui.activities.BaseActivity;
import com.objectedge.store.utils.LocalMessenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by eloor_000 on 4/6/2015.
 */
public class ProductsManager implements ModelObservable {

    public final static String TAG = "ProductsManager";

    private Context                       context;
    private List<ProductModel>            productModelList;
    //private SetModel currentSet;
    private List<ModelObserver>           observerList;
    //private Map<String,List<String>> searchRequestParams;
    private ProductModel                  currentProduct;
    private HashMap<String, ProductModel> hashProducts;
    private SkuModelList                  skusList;


    public ProductsManager(Context context) {
        this.context = context;
        productModelList = new ArrayList<>();
        //currentSet = new SetModel();
        observerList = new ArrayList<>();
        //currentProduct = new ProductModelWithSize();
        hashProducts = new HashMap<>();
        skusList = new SkuModelList();
    }


    public void clearSkus() {
        skusList = new SkuModelList();
    }


    private void setSkusList(SkuModelList skusList) {
        this.skusList.getLinks().clear();
        this.skusList.getItems().clear();
        this.skusList.getLinks().addAll(skusList.getLinks());
        this.skusList.getItems().addAll(skusList.getItems());
        notifyObservers(TAG, null);
    }


    public List<SkuModel> getSkus() {
        return skusList.getItems();
    }


    /*
    public SetModel getCurrentSet(){
        return currentSet;
    }

    public void setCurrentSet(SetModel currentSet){
        this.currentSet = currentSet;
    }
    */
    public List<ProductModel> getProductModelList() {
        return productModelList;
    }


    public void clearData() {
        productModelList = new ArrayList<>();
        //currentSet = new SetModel();
        observerList = new ArrayList<>();
        //currentProduct = new ProductModelWithSize();
        hashProducts = new HashMap<>();
    }


    public void setProductModelList(List<ProductModel> productModelList) {
        this.productModelList.clear();
        this.productModelList.addAll(productModelList);
        //addOnlyPositive(productModelList);
        notifyObservers(TAG, null);
    }


    public void addProducts(List<ProductModel> productModelList) {
        //addOnlyPositive(productModelList);
        this.productModelList.addAll(productModelList);
        notifyObservers(TAG, null);
    }


    public void clearAllSimpleProducts() {
        productModelList.clear();
        notifyObservers(TAG, null);
    }


    public ProductModel getProductById(String productId) {
        for(ProductModel productModel: productModelList) {
            if(productModel.getItemID().equals(productId)){
                return productModel;
            }
        }
        return null;
    }


    /*
    private void addOnlyPositive(List<ProductModel> simpleProductModelList){
        for(ProductModel productModel: simpleProductModelList){
            if(Double.parseDouble(productModel.getItemPrice()) > 0){
                this.productModelList.add(productModel);
            }
        }
    }
    */
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
        for (ModelObserver observer : observerList) {
            observer.modelChanged(message, error);
        }
    }


    public void loadProducts() {
        final NetworkManager networkManager = ((StoreApplication) context.getApplicationContext()).getNetworkManager();
        networkManager.getProducts(1, 20, new Callback<ResponseModel<ProductsListModel>>() {

            @Override
            public void onLoadStarted() {
                LocalMessenger.sendMessage(context, BaseActivity.TAG, BaseActivity.SHOW_PROGRESS, true);
            }


            @Override
            public void onLoadFinished(ResponseModel<ProductsListModel> response) {
                LocalMessenger.sendMessage(context, BaseActivity.TAG, BaseActivity.SHOW_PROGRESS, false);
                if (response.exception != null) {
                    notifyObservers(TAG, response.exception.getMessage());
                } else {
                    setProductModelList(response.model.getItems());
                }
            }
        });
    }


    public void getSkusByProduct(String productId) {
        NetworkManager networkManager = ((StoreApplication) context.getApplicationContext()).getNetworkManager();
        networkManager.getSkusByProduct(productId, new Callback<ResponseModel<SkuModelList>>() {

            @Override
            public void onLoadStarted() {
                //LocalMessenger.sendMessage(context, BaseActivity.TAG, BaseActivity.SHOW_PROGRESS, true);
            }


            @Override
            public void onLoadFinished(ResponseModel<SkuModelList> response) {
                //LocalMessenger.sendMessage(context, BaseActivity.TAG, BaseActivity.SHOW_PROGRESS, false);
                if (response.exception != null) {
                    Toast.makeText(context, response.exception.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    setSkusList(response.model);
                }
            }
        });

    }


    public void loadProductById(String productId, Callback<ResponseModel<ProductModel>> callback) {
        if (getProductById(productId) != null) {
            ResponseModel<ProductModel> response = new ResponseModel<>();
            response.model = getProductById(productId);
            callback.onLoadFinished(response);
        } else {
            ((StoreApplication)context.getApplicationContext()).getNetworkManager().getProductById(productId,callback);
        }
    }


    /*
    public void initSearchParams(List<String> searchFields){
        searchRequestParams = new HashMap<>();
        for (String type: searchFields){
            if(type.equals(Constants.PRICE)){
                List<String> optList = new ArrayList<>();
                optList.add("");
                optList.add("");
                searchRequestParams.put(type, optList);
            }else {
                searchRequestParams.put(type, new ArrayList<String>());
            }
        }
    }

    public List<String> getSearchParamsByName(String name){
        if(searchRequestParams != null) {
            List<String> paramsList = searchRequestParams.get(name);
            return paramsList;
        } else {
            return null;
        }
    }

    public void clearSearchParams(){
        if(searchRequestParams != null) {
            for(Map.Entry<String, List<String>> entry: searchRequestParams.entrySet()){
                if(entry.getKey().equals(Constants.PRICE)){
                    entry.getValue().set(0,"");
                    entry.getValue().set(1,"");
                }else{
                    entry.getValue().clear();
                }
            }
        }
    }

    public String makeSearchParamsString(){
        if(searchRequestParams == null){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        String priceString = makePriceString();
        if(!priceString.equals("")){
            builder.append("&");
            builder.append(priceString);
        }
        String brandString = makeRegularParamsString(Constants.BRAND);
        if(!brandString.equals("")){
            builder.append("&");
            builder.append(brandString);
        }
        String colourString = makeRegularParamsString(Constants.COLOUR);
        if(!colourString.equals("")){
            builder.append("&");
            builder.append(colourString);
        }
        String sizeString = makeRegularParamsString(Constants.SIZE);
        if(!sizeString.equals("")){
            builder.append("&");
            builder.append(sizeString);
        }
        String productTypeString = makeProductTypeParamsString();
        if(!productTypeString.equals("")){
            builder.append("&");
            builder.append(productTypeString);
        }
        return builder.toString();
    }

    private String makePriceString(){
        if(searchRequestParams == null){
            return "";
        }
        List<String> paramsList = searchRequestParams.get(Constants.PRICE);
        if(paramsList == null || paramsList.size() == 0){
            return "";
        }
        String minPrice = paramsList.get(0);
        String maxPrice = paramsList.get(1);
        if (!minPrice.equals("") && !maxPrice.equals("")) {
            return "min_price=" + StringUtils.getPriceFormat(minPrice) + "&" +
                   "max_price=" + StringUtils.getPriceFormat(maxPrice);
        } else if (!minPrice.equals("")) {
            return "min_price=" + StringUtils.getPriceFormat(minPrice);
        } else if (!maxPrice.equals("")) {
            return "max_price=" + StringUtils.getPriceFormat(maxPrice);
        } else {
            return "";
        }
    }

    private String makeRegularParamsString(String paramName){
        //if there ara no search params
        if(searchRequestParams == null){
            return "";
        }
        List<String> paramsList = searchRequestParams.get(paramName);
        //if there are no search params
        if(paramsList == null || paramsList.size() == 0){
            return "";
        }
        String withComa;
        StringBuilder builder = new StringBuilder();
        if(paramName.equals(Constants.BRAND)){
            builder.append("brand_name=");
        }else if(paramName.equals(Constants.COLOUR)){
            builder.append("colour=");
        }else if(paramName.equals(Constants.SIZE)){
            builder.append("size=");
        }
        for(int i = 0; i < paramsList.size(); i++){
            if(i == paramsList.size() - 1){
                builder.append(paramsList.get(i));
            } else {
                withComa = paramsList.get(i) + ",";
                builder.append(withComa);
            }
        }
        return builder.toString();
    }

    private String makeProductTypeParamsString(){
        //if there are no search params
        if(searchRequestParams == null){
            return "";
        }
        CategoriesManager categoriesManager =
                ((SurfStitchApplication)context.getApplicationContext()).getCategoriesManager();
        List<String> paramsList = searchRequestParams.get(Constants.PRODUCT_TYPE);
        //if there are no search params
        if(paramsList == null || paramsList.size() == 0){
            return "";
        }
        String withComa;
        String categoryId;
        StringBuilder builder = new StringBuilder();
        builder.append("category_node_id=");
        for(int i = 0; i < paramsList.size(); i++){
            categoryId = categoriesManager.getCategoryIdByName(paramsList.get(i));
            if(i == paramsList.size() - 1){
                builder.append(categoryId);
            }else {
                withComa = categoryId + ",";
                builder.append(withComa);
            }
        }
        return builder.toString();
    }
    */
    public void setCurrentProduct(ProductModel productModel) {
        currentProduct = productModel;
    }


    public ProductModel getCurrentProduct() {
        return currentProduct;
    }


    public void clearCurrentProduct() {
        currentProduct = new ProductModel();
    }
    /*
    public boolean isCurrentProductFull(){
        return currentProduct.getProductModel().getProduct_id() != null;
    }

    public void addProductToCache(ProductModel productModel){
        hashProducts.put(productModel.getProduct_id(),productModel);
    }

    public void removeProductFromCache(String productId){
        if(hashProducts.containsKey(productId)) {
            hashProducts.remove(productId);
        }
    }

    public boolean checkIfProductInTheCache(String productId){
        return hashProducts.containsKey(productId);
    }

    public ProductModel getProductFromCache(String productId){
        return hashProducts.get(productId);
    }
    */
}

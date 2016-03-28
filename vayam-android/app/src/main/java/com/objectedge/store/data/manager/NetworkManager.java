package com.objectedge.store.data.manager;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;
import com.objectedge.store.R;
import com.objectedge.store.StoreApplication;
import com.objectedge.store.annotations.Fake;
import com.objectedge.store.data.model.fake.CatalogModel;
import com.objectedge.store.data.model.order.*;
import com.objectedge.store.data.model.product.ProductModel;
import com.objectedge.store.data.model.product.ProductsListModel;
import com.objectedge.store.data.model.sku.SkuModelList;
import com.objectedge.store.data.model.user.AddUserModel;
import com.objectedge.store.data.model.user.UserModel;
import com.objectedge.store.data.model.user.UsersListModel;
import com.objectedge.store.data.parser.order.*;
import com.objectedge.store.data.parser.product.ProductParser;
import com.objectedge.store.data.parser.product.ProductsListParser;
import com.objectedge.store.data.parser.skus.SkusListParser;
import com.objectedge.store.data.parser.user.UserParser;
import com.objectedge.store.data.parser.user.UsersListParser;
import com.objectedge.store.network.*;
import com.objectedge.store.utils.HTTPConstants;
import com.objectedge.store.utils.Urls;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.net.CookieHandler;
import java.net.CookieManager;


/**
 * Created by eloor_000 on 9/9/2015.
 */
public class NetworkManager {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private boolean useLocalData = false;

    private Context context;


    public NetworkManager(Context context) {
        this.context = context;
        //set cookie support
        //TODO I don't know if it is needed
        CookieHandler.setDefault(new CookieManager());

    }


    /**
     * Check the internet connection of the device
     * Also, shoes a toast message if the internet is not available on the device.
     *
     * @return true if the internet connection exists, and false if opposite.
     */
    public boolean hasInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        boolean hasInternet = (activeNetInfo != null && activeNetInfo.isConnectedOrConnecting());
        if (!hasInternet) {
            Toast.makeText(context, context.getString(R.string.no_internet_error), Toast.LENGTH_SHORT).show();
        }
        return hasInternet;
    }


    /**
     * This method execute network request with params are given in RequestModel
     *
     * @param requestModel request model
     */
    private void invokeNetworkTask(RequestModel requestModel) {
        NetworkParserTask networkParserTask = new NetworkParserTask(requestModel);
        networkParserTask.execute();
    }


    /**
     * This method execute local task using files from assets
     *
     * @param fileName     asset's file
     * @param requestModel request model
     */
    private void invokeLocalTask(String fileName, RequestModel requestModel) {
        LocalParserTask localParserTask = new LocalParserTask(context, fileName, requestModel);
        localParserTask.execute();
    }


    /**
     * Get All users
     *
     * @param callback invoke this callback when users' data are received
     */
    public void getUsers(Callback<ResponseModel<UsersListModel>> callback) {
        if (!hasInternet()) {
            return;
        }
        RequestModel<ResponseModel<UsersListModel>> requestModel = new RequestModel<>();
        requestModel.setRequest(new Request.Builder().url(Urls.USERS_URI).build());
        requestModel.setParser(new UsersListParser());
        requestModel.setCallback(callback);
        invokeNetworkTask(requestModel);
    }


    /**
     * Get user by id
     *
     * @param userId   id of the existed user
     * @param callback invoke this callback when user's data are received
     */
    public void getUserById(String userId, Callback<ResponseModel<UserModel>> callback) {
        if (!hasInternet()) {
            return;
        }
        RequestModel<ResponseModel<UserModel>> requestModel = new RequestModel<>();
        requestModel.setRequest(new Request.Builder().url(Urls.USERS_URI + userId).build());
        requestModel.setParser(new UserParser());
        requestModel.setCallback(callback);
        invokeNetworkTask(requestModel);
    }


    /**
     * add user
     *
     * @param addUserModel
     * @param callback
     */
    public void addUser(AddUserModel addUserModel, Callback<ResponseModel<UserModel>> callback) {
        if (!hasInternet()) {
            return;
        }
        RequestModel<ResponseModel<UserModel>> requestModel = new RequestModel<>();
        RequestBody body = RequestBody.create(JSON, addUserModel.buildJson());
        Request request = new Request.Builder().url(Urls.USERS_URI).post(body)
                .addHeader(HTTPConstants.ACCEPT, HTTPConstants.APPLICATION_JSON)
                .addHeader(HTTPConstants.CONTENT_TYPE, HTTPConstants.APPLICATION_JSON).build();
        requestModel.setRequest(request);
        requestModel.setParser(new UserParser());
        requestModel.setCallback(callback);
        invokeNetworkTask(requestModel);
    }


    /**
     * Get All products
     *
     * @param callback invoke this callback when products' data are received
     */
    public void getProducts(Callback<ResponseModel<ProductsListModel>> callback) {
        if (!hasInternet()) {
            return;
        }
        RequestModel<ResponseModel<ProductsListModel>> requestModel = new RequestModel<>();
        requestModel.setRequest(new Request.Builder().url(Urls.PRODUCTS_URI).build());
        requestModel.setParser(new ProductsListParser());
        requestModel.setCallback(callback);
        invokeNetworkTask(requestModel);
    }


    /**
     * Get All products
     *
     * @param page     number
     * @param pageSize
     * @param callback invoke this callback when products' data are received
     */
    public void getProducts(int page, int pageSize, Callback<ResponseModel<ProductsListModel>> callback) {
        if (!hasInternet()) {
            return;
        }
        RequestModel<ResponseModel<ProductsListModel>> requestModel = new RequestModel<>();
        String url = Urls.PRODUCTS_URI + "?page=" + page + "&pageSize=" + pageSize;
        requestModel.setRequest(new Request.Builder().url(url).build());
        requestModel.setParser(new ProductsListParser());
        requestModel.setCallback(callback);
        if (useLocalData) {
            invokeLocalTask("products.json", requestModel);
        } else {
            invokeNetworkTask(requestModel);
        }
    }


    /**
     * Get user by id
     *
     * @param productId id of the existed product
     * @param callback  invoke this callback when user's data are received
     */
    public void getProductById(String productId, Callback<ResponseModel<ProductModel>> callback) {
        if (!hasInternet()) {
            return;
        }
        RequestModel<ResponseModel<ProductModel>> requestModel = new RequestModel<>();
        requestModel.setRequest(new Request.Builder().url(Urls.PRODUCTS_URI + productId).build());
        requestModel.setParser(new ProductParser());
        requestModel.setCallback(callback);
        if (useLocalData) {
            invokeLocalTask("products.json", requestModel);
        } else {
            invokeNetworkTask(requestModel);
        }
    }


    /**
     * Get skus by product
     *
     * @param productId
     * @param callback
     */
    public void getSkusByProduct(String productId, Callback<ResponseModel<SkuModelList>> callback) {
        if (!hasInternet()) {
            return;
        }
        RequestModel<ResponseModel<SkuModelList>> requestModel = new RequestModel<>();
        requestModel.setRequest(new Request.Builder().url(Urls.PRODUCTS_URI + productId + "/skus").build());
        requestModel.setParser(new SkusListParser());
        requestModel.setCallback(callback);
        invokeNetworkTask(requestModel);
    }


    /**
     * Create order
     *
     * @param createOrderModel
     * @param callback
     */
    public void createOrder(CreateOrderModel createOrderModel, Callback<ResponseModel<OrderModel>> callback) {
        if (!hasInternet()) {
            return;
        }
        RequestModel<ResponseModel<OrderModel>> requestModel = new RequestModel<>();
        RequestBody body = RequestBody.create(JSON, createOrderModel.buildJson());
        Request request = new Request.Builder().url(Urls.ORDERS_URI).post(body)
                .addHeader(HTTPConstants.ACCEPT, HTTPConstants.APPLICATION_JSON)
                .addHeader(HTTPConstants.CONTENT_TYPE, HTTPConstants.APPLICATION_JSON).build();
        requestModel.setRequest(request);
        requestModel.setParser(new OrderParser());
        requestModel.setCallback(callback);
        invokeNetworkTask(requestModel);
    }


    /**
     * Get order
     *
     * @param orderId
     */
    public void getOrder(String orderId, Callback<ResponseModel<OrderModel>> callback) {
        if (!hasInternet()) {
            return;
        }
        RequestModel<ResponseModel<OrderModel>> requestModel = new RequestModel<>();
        requestModel.setRequest(new Request.Builder().url(Urls.ORDERS_URI + orderId).build());
        requestModel.setParser(new OrderParser());
        requestModel.setCallback(callback);
        invokeNetworkTask(requestModel);
    }


    /**
     * Add product to order
     *
     * @param addItemToOrderModel
     * @param callback
     */
    public void addItemToOrder(String transactionId, AddItemToOrderModel addItemToOrderModel,
            Callback<ResponseModel<OrderModel.LineItem>> callback) {
        if (!hasInternet()) {
            return;
        }
        RequestModel<ResponseModel<OrderModel.LineItem>> requestModel = new RequestModel<>();
        String jsonString = addItemToOrderModel.buildJson();
        RequestBody body = RequestBody.create(JSON, jsonString);
        Request request = new Request.Builder().url(Urls.ORDERS_URI + transactionId + "/lineItems").post(body)
                .addHeader(HTTPConstants.ACCEPT, HTTPConstants.APPLICATION_JSON)
                .addHeader(HTTPConstants.CONTENT_TYPE, HTTPConstants.APPLICATION_JSON).build();
        requestModel.setRequest(request);
        requestModel.setParser(new LineItemParser());
        requestModel.setCallback(callback);
        invokeNetworkTask(requestModel);
    }


    /**
     * Add payment group
     *
     * @param transactionId
     * @param addPaymentGroupModel
     * @param callback
     */
    public void addPaymentGroupToOrder(String transactionId, AddPaymentGroupModel addPaymentGroupModel,
            Callback<ResponseModel<OrderModel.PaymentGroupModel>> callback) {
        if (!hasInternet()) {
            return;
        }
        RequestModel<ResponseModel<OrderModel.PaymentGroupModel>> requestModel = new RequestModel<>();
        RequestBody body = RequestBody.create(JSON, addPaymentGroupModel.buildJson());
        Request request = new Request.Builder().url(Urls.ORDERS_URI + transactionId + "/paymentGroups").post(body)
                .addHeader(HTTPConstants.ACCEPT, HTTPConstants.APPLICATION_JSON)
                .addHeader(HTTPConstants.CONTENT_TYPE, HTTPConstants.APPLICATION_JSON).build();
        requestModel.setRequest(request);
        requestModel.setParser(new PaymentParser());//LineItem parser should be
        requestModel.setCallback(callback);
        invokeNetworkTask(requestModel);
    }


    /**
     * add shipping method
     *
     * @param transactionId
     * @param addShippingToOrderModel
     * @param callback
     */
    public void addShippingToOrder(String transactionId, AddShippingToOrderModel addShippingToOrderModel,
            Callback<ResponseModel<OrderModel.ShippingGroupModel>> callback) {
        if (!hasInternet()) {
            return;
        }
        RequestModel<ResponseModel<OrderModel.ShippingGroupModel>> requestModel = new RequestModel<>();
        RequestBody body = RequestBody.create(JSON, addShippingToOrderModel.buildJson());
        Request request = new Request.Builder().url(Urls.ORDERS_URI + transactionId + "/shippingGroups").post(body)
                .addHeader(HTTPConstants.ACCEPT, HTTPConstants.APPLICATION_JSON)
                .addHeader(HTTPConstants.CONTENT_TYPE, HTTPConstants.APPLICATION_JSON).build();
        requestModel.setRequest(request);
        requestModel.setParser(new ShippingModelParser());
        requestModel.setCallback(callback);
        invokeNetworkTask(requestModel);
    }


    /**
     * Change shipping in order
     * @param transactionId
     * @param shippingId
     * @param addShippingToOrderModel
     * @param callback
     */
    public void changeShippingInOrder(String transactionId, String shippingId,
            AddShippingToOrderModel addShippingToOrderModel,
            Callback<ResponseModel<OrderModel.ShippingGroupModel>> callback) {
        if (!hasInternet()) {
            return;
        }
        RequestModel<ResponseModel<OrderModel.ShippingGroupModel>> requestModel = new RequestModel<>();
        RequestBody body = RequestBody.create(JSON, addShippingToOrderModel.buildJson());
        Request request = new Request.Builder().url(Urls.ORDERS_URI + transactionId + "/shippingGroups/" + shippingId)
                .put(body).addHeader(HTTPConstants.ACCEPT, HTTPConstants.APPLICATION_JSON)
                .addHeader(HTTPConstants.CONTENT_TYPE, HTTPConstants.APPLICATION_JSON).build();
        requestModel.setRequest(request);
        requestModel.setParser(new ShippingModelParser());
        requestModel.setCallback(callback);
        invokeNetworkTask(requestModel);
    }


    public void makeTransaction(TransactionModel transactionModel,
            Callback<ResponseModel<TransactionResponseModel>> callback) {
        if (!hasInternet()) {
            return;
        }
        RequestModel<ResponseModel<TransactionResponseModel>> requestModel = new RequestModel<>();
        String jsonString = transactionModel.buildJson();
        RequestBody body = RequestBody.create(JSON, jsonString);
        Request request = new Request.Builder().url(Urls.TRANSACTION_URI).post(body)
                .addHeader(HTTPConstants.ACCEPT, HTTPConstants.APPLICATION_JSON)
                .addHeader(HTTPConstants.CONTENT_TYPE, HTTPConstants.APPLICATION_JSON).build();
        requestModel.setRequest(request);
        requestModel.setParser(new TransactionResponseParser());
        requestModel.setCallback(callback);
        invokeNetworkTask(requestModel);
    }


    @Fake
    public void getCatalog(Callback<ResponseModel<CatalogModel>> callback) {
        final RequestModel<ResponseModel<CatalogModel>> requestModel = new RequestModel<>();
        requestModel.setCallback(callback);
        (new AsyncTask<Void, Void, ResponseModel<CatalogModel>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if (requestModel.getCallback() != null) {
                    requestModel.getCallback().onLoadStarted();
                }
            }


            @Override
            protected ResponseModel<CatalogModel> doInBackground(Void... params) {
                ResponseModel<CatalogModel> responseModel = new ResponseModel<>();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return responseModel;
            }


            @Override
            protected void onPostExecute(ResponseModel<CatalogModel> responseModel) {
                super.onPostExecute(responseModel);
                CategoriesManager categoriesManager = ((StoreApplication) context.getApplicationContext())
                        .getCategoriesManager();
                //fill by fake data
                categoriesManager.setFakeCategoriesList();
                CatalogModel catalogModel = new CatalogModel();
                catalogModel.setCategoryModelList(categoriesManager.getCategoriesList());
                responseModel.model = catalogModel;
                if (requestModel.getCallback() != null) {
                    requestModel.getCallback().onLoadFinished(responseModel);
                }
            }
        }).execute();
    }

}

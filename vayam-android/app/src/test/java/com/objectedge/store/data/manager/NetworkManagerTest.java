package com.objectedge.store.data.manager;

import com.objectedge.store.network.Callback;
import com.objectedge.store.network.ResponseModel;
import com.objectedge.store.data.model.product.ProductModel;
import com.objectedge.store.data.model.product.ProductsListModel;
import com.objectedge.store.data.model.user.UserModel;
import com.objectedge.store.data.model.user.UsersListModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by "Michael Katkov" on 9/11/2015.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, emulateSdk = 18)
public class NetworkManagerTest {

    private UsersListModel usersListModel = null;
    private UserModel userModel = null;
    private ProductsListModel productsListModel = null;
    private ProductModel productModel = null;

    @Before
    public void setup(){

    }

    @Test
    public void testGetUsers() {
        NetworkManager networkManager = new NetworkManager(Robolectric.application);
        Callback<ResponseModel<UsersListModel>> usersListModelCallback = new Callback<ResponseModel<UsersListModel>>() {
            @Override
            public void onLoadStarted() {

            }

            @Override
            public void onLoadFinished(ResponseModel<UsersListModel> responseModel) {
                usersListModel = responseModel.model;
            }
        };
        networkManager.getUsers(usersListModelCallback);
        //wait for background task
        Robolectric.runBackgroundTasks();
        assertNotNull(usersListModel);
        assertNotNull(usersListModel.getItems());
    }

    @Test
    public void testGetUserById(){
        NetworkManager networkManager = new NetworkManager(Robolectric.application);
        Callback<ResponseModel<UserModel>> userModelCallback = new Callback<ResponseModel<UserModel>>() {
            @Override
            public void onLoadStarted() {

            }

            @Override
            public void onLoadFinished(ResponseModel<UserModel> responseModel) {
                userModel = responseModel.model;
            }
        };
        networkManager.getUserById("200034", userModelCallback);
        //wait for background task
        Robolectric.runBackgroundTasks();
        assertNotNull(userModel);
        assertEquals(userModel.getContactInformation().getEmail().getEmailAddress(), "oetesting100rabh@gmail.com");
    }

    @Test
    public void testGetProducts() {
        NetworkManager networkManager = new NetworkManager(Robolectric.application);
        Callback<ResponseModel<ProductsListModel>> productsListModelCallback = new Callback<ResponseModel<ProductsListModel>>() {
            @Override
            public void onLoadStarted() {

            }

            @Override
            public void onLoadFinished(ResponseModel<ProductsListModel> responseModel) {
                productsListModel = responseModel.model;
            }
        };
        networkManager.getProducts(productsListModelCallback);
        //wait for background task
        Robolectric.runBackgroundTasks();
        assertNotNull(productsListModel);
        assertNotNull(productsListModel.getItems());
    }

    @Test
    public void testGetProductsById(){
        NetworkManager networkManager = new NetworkManager(Robolectric.application);
        Callback<ResponseModel<ProductModel>> productModelCallback = new Callback<ResponseModel<ProductModel>>() {
            @Override
            public void onLoadStarted() {

            }

            @Override
            public void onLoadFinished(ResponseModel<ProductModel> responseModel) {
                productModel = responseModel.model;
            }
        };
        networkManager.getProductById("pfinacuawb01", productModelCallback);
        //wait for background task
        Robolectric.runBackgroundTasks();
        assertNotNull(productModel);
        assertEquals(productModel.getX_thumbnailImageURL(), "http://www.finishline.com/images/products/xl1218012654.jpg");
    }
}

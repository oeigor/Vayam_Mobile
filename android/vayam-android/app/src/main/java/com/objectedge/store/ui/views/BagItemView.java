package com.objectedge.store.ui.views;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.*;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.objectedge.store.R;
import com.objectedge.store.StoreApplication;
import com.objectedge.store.data.manager.NetworkManager;
import com.objectedge.store.data.manager.OrdersManager;
import com.objectedge.store.data.manager.ProductsManager;
import com.objectedge.store.data.model.order.OrderModel;
import com.objectedge.store.data.model.product.ProductModel;
import com.objectedge.store.network.Callback;
import com.objectedge.store.network.ResponseModel;
import com.objectedge.store.utils.ImageUtils;

import java.util.List;


/**
 * Created by eloor_000 on 5/15/2015.
 */
public class BagItemView extends LinearLayout {

    public static final String TAG = "BagItemView";

    private ProgressBar          imageProgress;
    private ImageView            imageV;
    private TextView             titleTv;
    private TextView             priceTv;
    private TextView             quantityTv;
    private FloatingActionButton addOneButton;
    private FloatingActionButton substractOneButton;
    private FloatingActionButton deleteButton;

    private OrderModel.LineItem orderItemModel;

    private ProductsManager productsManager;
    private NetworkManager  networkManager;
    private OrdersManager   ordersManager;


    public BagItemView(Context context) {
        super(context);
        initManagers();
        initView();
    }


    public BagItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initManagers();
        initView();
    }


    private void initManagers() {
        networkManager = ((StoreApplication) getContext().getApplicationContext()).getNetworkManager();
        productsManager = ((StoreApplication) getContext().getApplicationContext()).getProductsManager();
        ordersManager = ((StoreApplication) getContext().getApplicationContext()).getOrdersManager();
    }


    private void initView() {
        inflate(getContext(), R.layout.item_bag_layout, this);
        imageProgress = (ProgressBar) findViewById(R.id.item_bag_image_progress);
        imageV = (ImageView) findViewById(R.id.item_bag_image);
        titleTv = (TextView) findViewById(R.id.item_bag_title);
        priceTv = (TextView) findViewById(R.id.item_bag_price);
        quantityTv = (TextView) findViewById(R.id.item_bag_quantity);
        addOneButton = (FloatingActionButton) findViewById(R.id.item_bag_add_one);
        substractOneButton = (FloatingActionButton) findViewById(R.id.item_bag_substruct_one);
        deleteButton = (FloatingActionButton) findViewById(R.id.item_bag_delete);
    }


    public void setData(OrderModel.LineItem orderItemModel) {
        this.orderItemModel = orderItemModel;
        String productId = orderItemModel.getProductIdFromMerHer();
        if (productId != null) {
            productsManager.loadProductById(productId, productCallback);
        }
        if(orderItemModel.getX_itemPriceInfo() != null) {
            priceTv.setText(String.valueOf(orderItemModel.getX_itemPriceInfo().getAmount()));
        }
        quantityTv.setText(String.valueOf(orderItemModel.getQuantity()));

        //There are no api for this
        addOneButton.setVisibility(GONE);
        substractOneButton.setVisibility(GONE);
        deleteButton.setVisibility(GONE);
    }


    private Callback<ResponseModel<ProductModel>> productCallback = new Callback<ResponseModel<ProductModel>>() {

        @Override
        public void onLoadStarted() {
            imageProgress.setVisibility(VISIBLE);
        }


        @Override
        public void onLoadFinished(ResponseModel<ProductModel> response) {
            if (response.exception != null) {
                Toast.makeText(getContext(), "Image loading failed", Toast.LENGTH_SHORT).show();
            } else {
                if (response.model.getX_thumbnailImageURL() != null) {
                    loadImage(response.model.getX_thumbnailImageURL());
                } else {
                    Toast.makeText(getContext(), "Image loading failed", Toast.LENGTH_SHORT).show();
                }
                titleTv.setText(response.model.getDescription().getShortValue());
                if(priceTv.getText().length() == 0){
                    priceTv.setText(response.model.getItemPriceFake());
                }
            }
        }
    };


    private void loadImage(String uri) {
        ImageLoader.getInstance().displayImage(uri, imageV, ImageUtils.getImageOptionsWithCacheOnDisk(),
                new SimpleImageLoadingListener() {

                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        imageProgress.setVisibility(View.VISIBLE);
                    }


                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        Toast.makeText(getContext(), "Image loading failed", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        imageProgress.setVisibility(View.GONE);
                    }
                });
    }

}

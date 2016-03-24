package com.objectedge.store.ui.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.objectedge.store.R;
import com.objectedge.store.StoreApplication;
import com.objectedge.store.data.manager.FavoritesManager;
import com.objectedge.store.data.manager.ProductsManager;
import com.objectedge.store.data.model.product.ProductModel;
import com.objectedge.store.ui.dialogs.DialogFactory;
import com.objectedge.store.ui.dialogs.SkusDialog;
import com.objectedge.store.utils.ImageUtils;

/**
 * Created by eloor_000 on 5/1/2015.
 */
public class FavoritesItemView extends LinearLayout {

    public static final String TAG = "FavoritesItemView";

    private ProgressBar progressBar;
    private ImageView imageV;
    private TextView titleTv;
    private TextView priceTv;
    private FloatingActionButton deleteFromFavorites;
    private FloatingActionButton addToCart;

    private ProductModel productModel;

    public FavoritesItemView(Context context) {
        super(context);
        initView();
    }

    public FavoritesItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView(){
        inflate(getContext(), R.layout.item_favorites_layout, this);
        progressBar = (ProgressBar) findViewById(R.id.item_favorites_progress);
        imageV = (ImageView) findViewById(R.id.item_favorites_image);
        titleTv = (TextView) findViewById(R.id.item_favorites_title);
        priceTv = (TextView) findViewById(R.id.item_favorites_price);
        deleteFromFavorites = (FloatingActionButton) findViewById(R.id.delete_from_favorites);
        addToCart = (FloatingActionButton) findViewById(R.id.add_to_cart);
    }

    private OnClickListener deleteFromFavoritesClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            FavoritesManager favoritesManager = ((StoreApplication)getContext().getApplicationContext()).getFavoritesManager();
            favoritesManager.removeFromFavorites(productModel);
        }
    };

    private OnClickListener addToCartClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            final SkusDialog skusDialog = SkusDialog.newInstance(productModel.getItemID());
            DialogFactory.showDialog((AppCompatActivity) getContext(), skusDialog);
        }
    };


    /**
     * fill the view by the data
     * @param productModel to fill fields of the view
     */
    public void setData(ProductModel productModel) {
        this.productModel = productModel;
        if (productModel.getX_thumbnailImageURL() != null) {
            loadImage(productModel.getX_thumbnailImageURL());
        }

        if (productModel.getDescription().getShortValue() != null) {
            titleTv.setText(productModel.getDescription().getShortValue());
        }
        priceTv.setText(productModel.getItemPriceFake());
        deleteFromFavorites.setOnClickListener(deleteFromFavoritesClick);
        addToCart.setOnClickListener(addToCartClick);
    }


    private void loadImage(String uri) {
        ImageLoader.getInstance().displayImage(uri, imageV, ImageUtils.getImageOptionsWithoutCacheOnDisk(),
                new SimpleImageLoadingListener() {

                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        progressBar.setVisibility(View.VISIBLE);
                    }


                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        //progress.setVisibility(View.GONE);
                    }


                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
}

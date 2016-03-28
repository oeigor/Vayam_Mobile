package com.objectedge.store.ui.views;

import android.content.Context;
import android.graphics.Bitmap;
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
import com.objectedge.store.data.model.product.ProductModel;
import com.objectedge.store.utils.ImageUtils;

import java.util.Random;

/**
 * Created by eloor_000 on 4/8/2015.
 */
public class ProductItemView extends LinearLayout {

    public final static String TAG = "ProductItemView";

    private ImageView productImage;
    private ProgressBar progress;
    private TextView productTitle;
    private TextView priceText;

    private ProductModel productModel;

    public ProductItemView(Context context) {
        super(context);
        initView();
    }

    public ProductItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView(){
        inflate(getContext(), R.layout.item_product, this);
        productImage = (ImageView) findViewById(R.id.item_product_image);
        progress = (ProgressBar) findViewById(R.id.item_product_progress);
        productTitle = (TextView) findViewById(R.id.item_product_title);
        priceText = (TextView) findViewById(R.id.item_product_price);
    }

    public void setData(ProductModel productModel){
        this.productModel = productModel;
        if(productModel.getX_thumbnailImageURL() != null){
            loadImage(productModel.getX_thumbnailImageURL());
        }
        if(productModel.getDescription() != null && productModel.getDescription().getShortValue() != null){
            productTitle.setText(productModel.getDescription().getShortValue());
        }
        priceText.setText(productModel.getItemPriceFake());
    }

    private void loadImage(String uri){
        ImageLoader.getInstance().displayImage(uri, productImage, ImageUtils.getImageOptionsWithoutCacheOnDisk(), new SimpleImageLoadingListener(){
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                //progress.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                progress.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener){
        super.setOnClickListener(onClickListener);
        productImage.setOnClickListener(onClickListener);
        productTitle.setOnClickListener(onClickListener);
        priceText.setOnClickListener(onClickListener);
    }

    @Override
    public void setTag(Object tag) {
        super.setTag(tag);
        productImage.setTag(tag);
        productTitle.setTag(tag);
        priceText.setTag(tag);
    }
}

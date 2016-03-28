package com.objectedge.store.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.objectedge.store.R;
import com.objectedge.store.data.model.product.ProductModel;
import com.objectedge.store.ui.views.TouchImageView;
import com.objectedge.store.utils.ImageUtils;

/**
 * Created by eloor_000 on 4/21/2015.
 */
public class ProductPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ProductModel productModel;
    private GestureDetector.OnDoubleTapListener onDoubleTapListener;

    public ProductPagerAdapter(Context context, ProductModel productModel,
                               GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        layoutInflater = LayoutInflater.from(context);
        this.productModel = productModel;
        this.onDoubleTapListener = onDoubleTapListener;
        this.context = context;
    }

    @Override
    public int getCount() {
        /*
        if(productModel.getMedia() == null) {
            return 0;
        }else{
            return productModel.getMedia().getImages().size();
        }
        */
        return 1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((FrameLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = layoutInflater.inflate(R.layout.item_product_pager, container, false);
        final TouchImageView imageView = (TouchImageView) itemView.findViewById(R.id.item_product_pager_image);
        imageView.setOnDoubleTapListener(onDoubleTapListener);
        final ProgressBar progressBar = (ProgressBar) itemView.findViewById(R.id.item_product_pager_progress);
        if(productModel.getX_thumbnailImageURL() != null){
            ImageLoader.getInstance().displayImage(productModel.getX_thumbnailImageURL(),
                    imageView, ImageUtils.getImageOptionsWithCacheOnDisk(),new SimpleImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {
                            progressBar.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                            Toast.makeText(context, "Image cannot be loaded", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            progressBar.setVisibility(View.GONE);
                        }
                    });
        } else {
            progressBar.setVisibility(View.VISIBLE);
        }
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }

}
package com.objectedge.store.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.io.OutputStream;

/**
 * Created by eloor_000 on 4/3/2015.
 */
public class ImageUtils {

    public static final String TAG = "ImageUtils";

    public static DisplayImageOptions getImageOptionsWithCacheOnDisk(){
       DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .displayer(new FadeInBitmapDisplayer(500))
                .build();
        return options;
    }

    public static DisplayImageOptions getImageOptionsWithoutCacheOnDisk(){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .build();
        return options;
    }

    public static Drawable getDrawable(Context context, int resource){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1){
            return context.getResources().getDrawable(resource,null);
        }else{
            return context.getResources().getDrawable(resource);
        }
    }

    public static void setBackground(Context context, View view, int drawableId){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(getDrawable(context, drawableId));
        } else {
            view.setBackgroundDrawable(getDrawable(context, drawableId));
        }
    }

    public static final String insertImage(ContentResolver cr, Bitmap source) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

        Uri url = null;
        String stringUrl = null;    /* value to be returned */

        try {
            url = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            if (source != null) {
                OutputStream imageOut = cr.openOutputStream(url);
                try {
                    source.compress(Bitmap.CompressFormat.JPEG, 50, imageOut);
                } finally {
                    imageOut.close();
                }

            } else {
                Log.e(TAG, "Failed to create thumbnail, removing original");
                cr.delete(url, null, null);
                url = null;
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to insert image", e);
            if (url != null) {
                cr.delete(url, null, null);
                url = null;
            }
        }

        if (url != null) {
            stringUrl = url.toString();
        }

        return stringUrl;
    }
}

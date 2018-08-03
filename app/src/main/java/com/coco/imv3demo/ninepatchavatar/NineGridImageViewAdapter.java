package com.coco.imv3demo.ninepatchavatar;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by ydx on 18-8-2.
 */

public abstract class NineGridImageViewAdapter<T> {
    public abstract void onDisplayImage(Context context, ImageView imageView, T t);

    public ImageView generateImageView(Context context){
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }
}
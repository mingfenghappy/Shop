package com.yumo.education.utils.Glide;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.yumo.education.R;

import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.widget.GFImageView;

public class GlideImageLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, final GFImageView imageView, Drawable defaultDrawable, int width, int height) {
        Glide.with(activity).load("file://" + path).placeholder(defaultDrawable).error(defaultDrawable).override(width, height)
                .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(new ImageViewTarget<GlideDrawable>(imageView) {
            @Override
            protected void setResource(GlideDrawable resource) {
                imageView.setImageDrawable(resource);
            }

            @Override
            public void setRequest(Request request) {
                imageView.setTag(R.id.img_dialog, request);
            }

            @Override
            public Request getRequest() {
                return (Request) imageView.getTag(R.id.img_dialog);
            }
        });
    }

    @Override
    public void clearMemoryCache() {

    }
}

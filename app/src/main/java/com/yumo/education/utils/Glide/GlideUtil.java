package com.yumo.education.utils.Glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yumo.education.R;

/**
 * 类描述：Glide封装实体类
 * 作  者：蔡佳彬
 * 时  间：2017-07-11
 * 修改备注：
 */
public class GlideUtil {
    private static int LoadingImg = R.drawable.banner_gray;
    private static int ErrorImg = R.drawable.banner_gray;

    /**
     * 设置普通图片
     *
     * @param context
     * @param Url
     * @param iv
     */
    public static void ShowImage(Context context, String Url, ImageView iv) {
        Glide.with(context).load(Url).asBitmap().placeholder(LoadingImg).error(ErrorImg).into(iv);
    }

    /**
     * 设置圆角图片
     *
     * @param context
     * @param url
     * @param iv
     * @param rudius
     */
    public static void ShowRoundCornerImg(Context context, String url, ImageView iv, int rudius) {
        Glide.with(context).load(url).placeholder(LoadingImg).error(ErrorImg).transform(new GlideRoundTransform(context, rudius)).into(iv);
    }

    /**
     * 设置圆形图片
     *
     * @param context
     * @param url
     * @param iv
     */
    public static void ShowCircleImg(Context context, String url, ImageView iv) {
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.banner_gray)
                .error(R.drawable.banner_gray)
                .transform(new GlideCircleTransform(context))
                .into(iv);
    }

    public static void ShowCircleImg(Context context, String url, ImageView iv, int imgPic) {
        Glide.with(context)
                .load(url)
                .placeholder(imgPic)
                .error(imgPic)
                .transform(new GlideCircleTransform(context))
                .into(iv);
    }

    public static void setLoadingImg(int loadingImgId) {
        LoadingImg = loadingImgId;
    }
}

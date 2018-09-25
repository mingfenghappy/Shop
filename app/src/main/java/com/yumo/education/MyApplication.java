package com.yumo.education;

import android.app.Application;

import com.yumo.education.utils.Glide.GlideImageLoader;
import com.yumo.education.utils.SharedPreferenceUtil;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;

public class MyApplication extends Application {
    private static MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        SharedPreferenceUtil.init(this, "BasePrefreence");
        configurationGallerFinal();
    }

    public static MyApplication getApp() {
        if (app != null && app instanceof MyApplication) {
            return app;
        } else {
            app = new MyApplication();
            app.onCreate();
            return app;
        }
    }

    /**
     * GallerFinal配置
     */
    private void configurationGallerFinal() {
        //配置主题
        ThemeConfig themeConfig = new ThemeConfig.Builder().build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true).setEnableCrop(true).setEnableEdit(true)
                .setEnablePreview(true).setEnableRotate(true).setCropSquare(true).build();
        ImageLoader imageLoader = new GlideImageLoader();
        //配置核心信息
        CoreConfig coreConfig = new CoreConfig.Builder(this, imageLoader, themeConfig)
                .setFunctionConfig(functionConfig).build();
        GalleryFinal.init(coreConfig);
    }
}

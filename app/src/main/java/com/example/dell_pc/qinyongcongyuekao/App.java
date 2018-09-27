package com.example.dell_pc.qinyongcongyuekao;

import android.app.Application;

import com.example.topgridlibrary.topgrid.app.AppApplication;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/*
 * 作者：秦永聪
 *日期：2018/9/25
 * */public class App extends AppApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration=ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
    }
}

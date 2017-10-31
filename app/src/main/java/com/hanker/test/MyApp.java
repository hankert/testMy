package com.hanker.test;

import android.app.Application;

import com.hanker.core.net.app.Matcha;

/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/10/25.
 */

public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Matcha.init(this)
                .withApiHost("http://baidu.com")
                .withLoaderDelayed(1000)
                .configure();
    }
}

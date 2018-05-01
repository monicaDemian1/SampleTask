package com.etisalat.sampletask.common.helper;

import android.app.Application;

/**
 * Created by monica on 4/30/2018.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
       FontManager.initAppFonts(getApplicationContext());

    }
}

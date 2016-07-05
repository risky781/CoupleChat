package com.bearapp.couplechat;

import android.app.Application;
import android.util.Log;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by Henry.Ren on 16/7/5.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /*
        初始化融云
         */
        RongIM.init(this);
    }

}

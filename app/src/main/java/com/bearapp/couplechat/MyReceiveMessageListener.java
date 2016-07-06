package com.bearapp.couplechat;

import android.content.Context;
import android.os.Vibrator;

import java.util.concurrent.TimeUnit;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

/**
 * Created by Henry.Ren on 7/5/16.
 */
public class MyReceiveMessageListener implements RongIMClient.OnReceiveMessageListener {
    private long LAST_VIB = System.currentTimeMillis();
    private long VIB_INTERVAL = TimeUnit.MINUTES.toMillis(5);

    @Override
    public boolean onReceived(Message message, int i) {
        long passed = System.currentTimeMillis() - LAST_VIB;
        if (passed > VIB_INTERVAL) {
            Vibrator vibrator = (Vibrator) App.getInstance().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(500);
            LAST_VIB = System.currentTimeMillis();
        }
        return true;
    }
}

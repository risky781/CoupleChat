package com.bearapp.couplechat;

import android.content.Context;
import android.os.Vibrator;

import java.util.concurrent.TimeUnit;

import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

/**
 * Created by Henry.Ren on 7/5/16.
 */
public class MyPushMessageReceiver extends PushMessageReceiver {

    private long LAST_VIB = System.currentTimeMillis();
    private long VIB_INTERVAL = TimeUnit.MINUTES.toMillis(5);

    @Override
    public boolean onNotificationMessageArrived(Context context, PushNotificationMessage pushNotificationMessage) {
        long passed = System.currentTimeMillis() - LAST_VIB;
        if (passed > VIB_INTERVAL) {
            Vibrator vibrator = (Vibrator) App.getInstance().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(500);
            LAST_VIB = System.currentTimeMillis();
        }
        return true;
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushNotificationMessage pushNotificationMessage) {
        return true;
    }
}

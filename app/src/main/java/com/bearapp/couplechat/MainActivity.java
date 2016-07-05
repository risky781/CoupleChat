package com.bearapp.couplechat;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CoupleChat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // 连接到融云
        connect();
        final View.OnClickListener onClickListener = new View.OnClickListener() {
            private UserInfo userInfo = new UserInfo(Config.USER, Config.USER, null);

            @Override
            public void onClick(View v) {
                if (RongIM.getInstance() != null) {
                    RongIM.getInstance().setCurrentUserInfo(userInfo);
                    RongIM.getInstance().setMessageAttachedUserInfo(true);
                    RongIM.getInstance().startPrivateChat(MainActivity.this, Config.CHAT_TO, "与" + Config.CHAT_TO + "聊天中");
                }
            }
        };
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Snackbar.make(v, getString(R.string.text_something_happy), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.action_enter), onClickListener).show();
                return true;
            }
        });
    }

    private void connect() {
        RongIM.connect(Config.TOKEN, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Log.e(TAG, "onTokenIncorrect");
            }

            @Override
            public void onSuccess(String s) {
                Log.e(TAG, "onSuccess " + s);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e(TAG, "onError " + errorCode);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

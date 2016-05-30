package com.rainfool.appstoreprogressbar;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.rainfool.appstoreprogressbar.view.AppStoreProgressbar;

public class MainActivity extends Activity {

    AppStoreProgressbar mProgressbar;

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressbar = (AppStoreProgressbar) findViewById(R.id.mProgress);

        Log.d("rainfool","mprogressbar == null?" + (mProgressbar == null));
//        mProgressbar.setProgress(50);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgressbar.setProgress(38);
            }
        },300);
    }
}

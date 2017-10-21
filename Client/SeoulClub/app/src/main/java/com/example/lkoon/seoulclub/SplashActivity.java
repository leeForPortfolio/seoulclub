package com.example.lkoon.seoulclub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.lkoon.seoulclub.view.MainActivity;

/**
 * Created by lkoon on 2017-10-02.
 */

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(getApplication(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}
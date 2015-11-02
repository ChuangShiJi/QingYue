package com.chsj.qingyue;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;


/**
 * 活动扉页
 */
public class SplashActivity extends AppCompatActivity implements  Runnable{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        Thread thread = new Thread(this);

        thread.start();

    }


    /**
     * 实现Run方法：
     */
    @Override
    public void run() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * 开启新的Activity
         */
        Intent intent = new Intent();

        //TODO 显示主界面
        intent.setClass(this, MainActivity.class);

        startActivity(intent);

        finish();

    }

}

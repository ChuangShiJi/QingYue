package com.chsj.qingyue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class SettingNetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_net);

        setTitle("");
        Window window = getWindow();

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, 500);
    }


    /**
     * 设置移动网络
     *
     * @param view
     */
    public void txtSetWire(View view) {
        startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
        finish();
    }

    /**
     * 设置无线网络
     *
     * @param view
     */
    public void txtSetWifi(View view) {
        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        finish();
    }
}

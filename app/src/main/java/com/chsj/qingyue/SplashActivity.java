package com.chsj.qingyue;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * 活动扉页
 */
public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private CheckBox checkBox;
    private int anInt;
    private SharedPreferences sp;

    private RelativeLayout relative;

    private TextView txt_agree, txt_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        checkBox = (CheckBox) findViewById(R.id.splash_chexkbox);
        relative = (RelativeLayout) findViewById(R.id.relative_window);

        //检查sp中数据：
        sp = getSharedPreferences(Constants.SP_NAME, MODE_PRIVATE);
        anInt = sp.getInt(Constants.SP_KEY_IS_AGREE, 0);

        txt_agree = (TextView) findViewById(R.id.splash_agree);
        txt_exit = (TextView) findViewById(R.id.splash_exit);

        txt_agree.setOnClickListener(this);
        txt_exit.setOnClickListener(this);

        isShow();//是否显示   弹出框

    }

    private void isShow() {

        if (anInt == 1) { //设置  不显示

            relative.setVisibility(View.INVISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        toNewActivity();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private void toNewActivity() {
        /**
         * 开启新的Activity
         */
        Intent intent = new Intent();

        //TODO 显示主界面
        intent.setClass(this, MainActivity.class);

        startActivity(intent);

        finish();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.splash_agree:

                if (checkBox.isChecked()) {

                    SharedPreferences.Editor edit = sp.edit();
                    edit.putInt(Constants.SP_KEY_IS_AGREE, 1);//添加选中
                    edit.commit();

                }

                toNewActivity();

                break;

            case R.id.splash_exit:

                finish();

                break;
        }
    }
}

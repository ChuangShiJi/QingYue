package com.chsj.qingyue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
    }


    public void setOperate(View view) {
        if (view != null) {
            int id = view.getId();
            switch (id) {
//                TODO 设置界面的功能实现
//                模式设置
                case R.id.setting_model:
                    Toast.makeText(this, "切换模式", Toast.LENGTH_SHORT).show();
                    break;
//                应用评分
                case R.id.setting_grade:
                    break;
//                用户回馈
                case R.id.setting_feedback:
                    break;
//                用户协议
                case R.id.setting_user_agreement:
                    break;
//                退出当前登录
                case R.id.setting_logout:
                    break;
//                回退按键,退出当前的Activity
                case R.id.setting_back:
                    finish();
                    break;

            }
        }

    }


}

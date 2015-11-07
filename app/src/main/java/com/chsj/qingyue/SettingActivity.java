package com.chsj.qingyue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;

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
                    Toast.makeText(this, getResources().getString(R.string.switch_mode), Toast.LENGTH_SHORT).show();
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
                    logout();
                    break;
//                回退按键,退出当前的Activity
                case R.id.setting_back:
                    finish();
                    break;

            }
        }

    }

//    退出当前登录

    private void logout() {
//        初始化sharesdk
        ShareSDK.initSDK(this);
        Platform qq = ShareSDK.getPlatform(this, QQ.NAME);
        Platform sina = ShareSDK.getPlatform(this, SinaWeibo.NAME);
        Platform wechat = ShareSDK.getPlatform(this, Wechat.NAME);
        boolean logout = false;
        if (qq.isValid()) {
            qq.removeAccount();
            logout = true;
        } else if (sina.isValid()) {
            sina.removeAccount();
            logout = true;
        } else if (wechat.isValid()) {
            wechat.removeAccount();
            logout = true;
        }
        if (logout) {
            Toast.makeText(this, "退出当前账号成功", Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(Constants.INTENT_ACTION_LOGOUT);
            sendBroadcast(intent);
        }else {
            Toast.makeText(this, "当前无账号登录", Toast.LENGTH_SHORT).show();

        }

//isValid和removeAccount不开启线程，会直接返回。
    }


}

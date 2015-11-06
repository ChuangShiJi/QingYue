package com.chsj.qingyue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class LoginActivity extends AppCompatActivity implements PlatformActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
//        初始化ShareSDK
        ShareSDK.initSDK(this);
    }

    //    点击事件的实现
    public void loginOperate(View view) {
        if (view != null) {
            int id = view.getId();
            switch (id) {
//                回退按键退出当前的Activity
                case R.id.login_back:
                    finish();
                    break;
//                登录按键
                case R.id.login_button:
                    break;
//                忘记密码处理
                case R.id.login_forgetpwd:
                    break;
//                注册新用户
                case R.id.login_new_user_register:
                    break;
//                第三方微信登录
                case R.id.login_wechat:
                    Platform wechat = ShareSDK.getPlatform(this, Wechat.NAME);
                    wechat.SSOSetting(false);
                    wechat.setPlatformActionListener(this);
                    wechat.authorize();
                    break;
//                第三方qq登录
                case R.id.login_qq:

                    Platform qq = ShareSDK.getPlatform(this, QQ.NAME);
                    qq.setPlatformActionListener(this);
                    qq.authorize();
                    break;
//                第三方新浪登录
                case R.id.login_sina:
                    Platform sina = ShareSDK.getPlatform(this, SinaWeibo.NAME);
                    sina.setPlatformActionListener(this);
                    sina.authorize();
                    break;
            }
        }
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

        Toast.makeText(this, getResources().getString(R.string.authorization_success), Toast.LENGTH_SHORT).show();
        String name=platform.getDb().getUserName();
        String userIcon=platform.getDb().getUserIcon();

    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Toast.makeText(this, getResources().getString(R.string.authorization_fail), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel(Platform platform, int i) {
        Toast.makeText(this, getResources().getString(R.string.authorization_cancle), Toast.LENGTH_SHORT).show();
        String name=platform.getDb().getUserName();
        String userIcon=platform.getDb().getUserIcon();
    }
}

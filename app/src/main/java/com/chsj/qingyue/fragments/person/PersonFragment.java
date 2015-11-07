package com.chsj.qingyue.fragments.person;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chsj.qingyue.Constants;
import com.chsj.qingyue.LoginActivity;
import com.chsj.qingyue.PersonalCenterActivity;
import com.chsj.qingyue.R;
import com.chsj.qingyue.RegardActivity;
import com.chsj.qingyue.SettingActivity;
import com.squareup.picasso.Picasso;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends Fragment implements View.OnClickListener {
    TextView settingTV, aboutTV, moreAppTV;
    static TextView loginTV;
    private static boolean isLogin;
    private String nickname;
    private String icon;

    public PersonFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.person_fragment, container, false);
        loginTV = (TextView) inflate.findViewById(R.id.person_login);
        loginTV.setOnClickListener(this);
        settingTV = (TextView) inflate.findViewById(R.id.perosn_setting);
        settingTV.setOnClickListener(this);
        aboutTV = (TextView) inflate.findViewById(R.id.person_about);
        aboutTV.setOnClickListener(this);
        moreAppTV = (TextView) inflate.findViewById(R.id.person_moreApp);
        moreAppTV.setOnClickListener(this);
        return inflate;
    }

    @Override
    public void onResume() {
        super.onResume();
//读取登录用户的信息
        ShareSDK.initSDK(getActivity());
        Platform platforms[] = new Platform[3];
        platforms[0] = ShareSDK.getPlatform(getActivity(), Wechat.NAME);
        platforms[1] = ShareSDK.getPlatform(getActivity(), QQ.NAME);
        platforms[2] = ShareSDK.getPlatform(getActivity(), SinaWeibo.NAME);
        for (int i = 0; i < platforms.length; i++) {

            nickname = platforms[i].getDb().get("nickname");
            icon = platforms[i].getDb().get("icon");
            if (nickname != null && !nickname.isEmpty()) {
                isLogin = true;
                loginTV.setText(nickname);
                break;

            }

        }


    }

    //监听事件实现不同界面的跳转
    @Override
    public void onClick(View v) {
        if (v != null) {
            int id = v.getId();
            Intent intent = null;
            switch (id) {
//                登录界面跳转
                case R.id.person_login:
//                    如果已经登陆进入个人中心，否则进入登陆界面
                    if (isLogin) {
                        intent = new Intent(getActivity(), PersonalCenterActivity.class);
                        intent.putExtra("icon", icon);
                        intent.putExtra("nickName", nickname);
                    } else {
                        intent = new Intent(getActivity(), LoginActivity.class);

                    }
                    break;
//                设置界面跳转
                case R.id.perosn_setting:
                    intent = new Intent(getActivity(), SettingActivity.class);
                    break;
//                关于界面跳转
                case R.id.person_about:
                    intent = new Intent(getActivity(), RegardActivity.class);
                    break;
//                应用推荐跳转
                case R.id.person_moreApp:
                    break;
            }
            if (intent != null) {
                startActivity(intent);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //    注册广播接收者处理登录问题

    public static class LoginReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                if (action.equals(Constants.INTENT_ACTION_LOGOUT)) {
                    loginTV.setText("立即登录");
                    isLogin = false;

                } else if (action.equals((Constants.INTENT_ACTION_LOGIN))) {

                }

            }
        }
    }
}

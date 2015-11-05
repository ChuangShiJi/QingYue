package com.chsj.qingyue.fragments.person;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chsj.qingyue.LoginActivity;
import com.chsj.qingyue.R;
import com.chsj.qingyue.RegardActivity;
import com.chsj.qingyue.SettingActivity;
import com.squareup.picasso.Picasso;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends Fragment implements View.OnClickListener {
    TextView loginTV, settingTV, aboutTV, moreAppTV;

    public PersonFragment() {
        // Required empty public constructor
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
        ShareSDK.initSDK(getActivity());
        Platform qq = ShareSDK.getPlatform(getActivity(), QQ.NAME);
        String accessToken = qq.getDb().getToken(); // 获取授权token
        String openId = qq.getDb().getUserId(); // 获取用户在此平台的ID
        String nickname = qq.getDb().get("nickname"); // 获取用户昵称
// 接下来执行您要的操作
//        Picasso.with(getActivity()).load().into(loginTV);
        if (nickname != null&&!nickname.isEmpty()) {

            loginTV.setText(nickname);

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
                    intent = new Intent(getActivity(), LoginActivity.class);
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
}

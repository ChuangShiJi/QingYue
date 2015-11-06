package com.chsj.qingyue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PersonalCenterActivity extends AppCompatActivity {

    String nickName, icon;
    private ImageView iconUser;
    private TextView nickNameUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_center_activity);
        iconUser = (ImageView) findViewById(R.id.personal_center_icon);
        nickNameUser = (TextView) findViewById(R.id.personal_center_nickName);

        nickName = getIntent().getStringExtra("nickName");
        icon = getIntent().getStringExtra("icon");
        if (nickNameUser != null) {
            nickNameUser.setText(nickName);
        }
        if (icon != null && iconUser != null) {
            Picasso.with(this).load(icon).into(iconUser);
        }
    }

    //按键的监听方法
    public void personcenterOperate(View view) {
        if (view != null) {
            int id = view.getId();
            switch (id) {
                case R.id.personal_center_stowBtn:
                    Intent intent=new Intent(this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.perosnal_back:
                    finish();
                    break;
            }
        }

    }
}

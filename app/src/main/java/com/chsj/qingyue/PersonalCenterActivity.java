package com.chsj.qingyue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
        iconUser= (ImageView) findViewById(R.id.person_center_icon);
        nickNameUser= (TextView) findViewById(R.id.person_center_nickName);

        nickName = getIntent().getStringExtra("nickName");
        icon = getIntent().getStringExtra("icon");
        if (nickNameUser != null) {
            nickNameUser.setText(nickName);
        }
        if (icon != null&&iconUser!=null) {
            Picasso.with(this).load(icon).into(iconUser);
        }
    }


}

package com.chsj.qingyue;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.net.Uri;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.chsj.qingyue.fragments.article.ArticleFragment;
import com.chsj.qingyue.fragments.homepage.HomePageFragment;
import com.chsj.qingyue.fragments.object.SongFragment;
import com.chsj.qingyue.fragments.person.PersonFragment;
import com.chsj.qingyue.fragments.question.QuestionFragment;
import com.chsj.qingyue.fragments.question.QuestionFragmentItem;
import com.chsj.qingyue.tools.NetWorkUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton first, article, question, things, personal;

    private ImageView share;
    private boolean isNetWorkAvalable;

    //本地广播管理器对象：
    private LocalBroadcastManager localBroadcastManager;
    //广播接受者：
    private ShareBroadcastReceiver shareBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        isNetWorkAvalable = NetWorkUtils.isConnect(this);


        first = (RadioButton) findViewById(R.id.main_tab_item_first);
        article = (RadioButton) findViewById(R.id.main_tab_item_article);
        question = (RadioButton) findViewById(R.id.main_tab_item_problem);
        things = (RadioButton) findViewById(R.id.main_tab_item_things);
        personal = (RadioButton) findViewById(R.id.main_tab_item_personal);

        share = (ImageView) findViewById(R.id.title_share);

        first.setOnClickListener(this);
        article.setOnClickListener(this);
        question.setOnClickListener(this);
        things.setOnClickListener(this);
        personal.setOnClickListener(this);

        if (!isNetWorkAvalable) {//当前没有网络，进入 网络设置界面
            //TODO 进行网络设置
            Intent intent = new Intent(MainActivity.this, SettingNetActivity.class);
            startActivity(intent);
        } else {//有网络，加载默认的fragment
            HomePageFragment fragment = new HomePageFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_id, fragment)
                    .commit();
        }
        //实例化广播接受者：
        localBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        shareBroadcastReceiver = new ShareBroadcastReceiver();
        //注册本地广播接受者：
        localBroadcastManager.registerReceiver(shareBroadcastReceiver, new IntentFilter(Constants.GET_DATA_TO_SHARE));

        //添加分享的单击事件：
        share.setOnClickListener(this);
    }

    //初始化单击事件：
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.main_tab_item_first:

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_id, new HomePageFragment())
                        .commit();
                break;

            case R.id.main_tab_item_article:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_id, new ArticleFragment())
                        .commit();
                break;

            case R.id.main_tab_item_problem:

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_id, new QuestionFragment())
                        .commit();
                break;

            case R.id.main_tab_item_things:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_id, new SongFragment())
                        .commit();
                break;

            case R.id.main_tab_item_personal:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_id, new PersonFragment())
                        .commit();
                break;

            //点击分享：
            case R.id.title_share:

                if (shareBroadcastReceiver.data != null) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT,shareBroadcastReceiver.data);
                    startActivity(intent);
                }else{
                    Toast.makeText(this,"当前无数据可分享",Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }


    @Override
    protected void onDestroy() {
        //取消注册  广播
        localBroadcastManager.unregisterReceiver(shareBroadcastReceiver);

        super.onDestroy();
    }


    //接受数据
    class ShareBroadcastReceiver extends BroadcastReceiver {
        private String data;//传入的数据：
        @Override
        public void onReceive(Context context, Intent intent) {
            data = intent.getStringExtra(Constants.DATA_TO_EXTRA);
        }
    }
}

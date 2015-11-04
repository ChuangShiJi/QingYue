package com.chsj.qingyue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.chsj.qingyue.fragments.article.ArticleFragment;
import com.chsj.qingyue.fragments.homepage.HomePageFragment;
import com.chsj.qingyue.fragments.object.SongFragment;
import com.chsj.qingyue.fragments.person.PersonFragment;
import com.chsj.qingyue.fragments.question.QuestionFragment;
import com.chsj.qingyue.tools.NetWorkUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton first, article, question, things, personal;

    private boolean isNetWorkAvalable;

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
        }

    }
}

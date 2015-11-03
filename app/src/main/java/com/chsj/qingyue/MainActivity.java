package com.chsj.qingyue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.chsj.qingyue.fragments.article.ArticleFragment;
import com.chsj.qingyue.fragments.homepage.HomePageFragment;
import com.chsj.qingyue.fragments.object.ObjectFragment;
import com.chsj.qingyue.fragments.person.PersonFragment;
import com.chsj.qingyue.fragments.question.QuestionFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton first,article,question,things,personal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


    }

    //初始化单击事件：
    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.main_tab_item_first:

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_id,new HomePageFragment())
                        .commit();

                break;


            case R.id.main_tab_item_article:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_id,new ArticleFragment())
                        .commit();
                break;

            case R.id.main_tab_item_problem:

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_id,new QuestionFragment())
                        .commit();

                break;

            case R.id.main_tab_item_things:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_id,new ObjectFragment())
                        .commit();
                break;

            case R.id.main_tab_item_personal:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_id,new PersonFragment())
                        .commit();
                break;
        }

    }
}

package com.chsj.qingyue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chsj.qingyue.fragments.homepage.HomePageFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomePageFragment fragment = new HomePageFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_id, fragment)
                .commit();

    }


}

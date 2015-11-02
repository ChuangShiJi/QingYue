package com.chsj.qingyue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        startActivity(intent);
        super.onBackPressed();
    }

    public void btnJumpMain(View view){

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }

}

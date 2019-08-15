package com.hongx.host;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hongx.module1.Module1Activity;
import com.hongx.module2.Module2Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void module1Jump(View view) {
        Intent intent = new Intent(MainActivity.this, Module1Activity.class);
        startActivity(intent);
    }

    public void module2Jump(View view) {
        Intent intent = new Intent(MainActivity.this, Module2Activity.class);
        startActivity(intent);
    }
}

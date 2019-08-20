package com.hongx.annotationpro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hongx.compiler.annotation.Route;
import com.hongx.processor.auto.Main$$Hx;


@Route(path = "Main",group = "Activity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeText(View view) {
        Log.i("MainActivity","click");
        Main$$Hx main$$Hx = new Main$$Hx();
        ((TextView)view).setText(main$$Hx.getMessage());
    }
}

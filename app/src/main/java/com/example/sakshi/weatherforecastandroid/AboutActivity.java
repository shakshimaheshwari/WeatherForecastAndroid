package com.example.sakshi.weatherforecastandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ImageView img = (ImageView)findViewById(R.id.imageButton);
        img.setImageResource(R.mipmap.weatherbackground);
    }
}

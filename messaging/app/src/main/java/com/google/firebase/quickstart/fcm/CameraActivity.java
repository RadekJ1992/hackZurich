package com.google.firebase.quickstart.fcm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // get Intent from MainActivity
        Intent intent = getIntent();
        String imageAsString = intent.getStringExtra("key");

        setImageViewFromString(imageAsString);

    }

    private void setImageViewFromString(String imageAsView) {
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        // convert String to image

        // set imageview image
    }

}

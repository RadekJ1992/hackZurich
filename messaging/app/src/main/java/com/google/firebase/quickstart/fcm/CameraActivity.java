package com.google.firebase.quickstart.fcm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // get Intent from MainActivity
        Intent intent = getIntent();
        String imageURL = intent.getStringExtra("key").replace("{body=","").replace("}","");

        WebView webView = (WebView) findViewById(R.id.webView);

        WebView web = (WebView) findViewById(R.id.webView);
        web.loadUrl(imageURL);

    }

}

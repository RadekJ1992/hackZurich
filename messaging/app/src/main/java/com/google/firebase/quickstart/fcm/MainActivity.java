/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.firebase.quickstart.fcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private final String USER_AGENT = "Mozilla/5.0";
    private static final String TOKEN_ID = "token_id";

    MyReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // If a notification message is tapped, any data accompanying the notification
        // message is available in the intent extras. In this sample the launcher
        // intent is fired when the notification is tapped, so any accompanying data would
        // be handled here. If you want a different intent fired, set the click_action
        // field of the notification message to the desired intent. The launcher intent
        // is used when no click_action is specified.
        //
        // Handle possible data accompanying notification message.
        // [START handle_data_extras]


        if (getIntent().getExtras() != null) {
            // case 1: the ittent is from settingsActivity
            if (getIntent().getExtras().get("settings") != null) {
                Log.d(TAG,"IN HERE2");
                new sendtoServer().execute(FirebaseInstanceId.getInstance().getToken(), getIntent().getExtras().get("settings").toString());
            }
            else {

                String value = getIntent().getExtras().get("image_url").toString();
                Intent myIntent = new Intent(MainActivity.this, CameraActivity.class);
                myIntent.putExtra("key", (String) value); //Optional parameters
                MainActivity.this.startActivity(myIntent);
                Log.d(TAG,"IN HERE");

            }
        }
        // [END handle_data_extras]

        ImageButton subscribeButton = (ImageButton) findViewById(R.id.subscribeButton);
        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // [START subscribe_topics]
                FirebaseMessaging.getInstance().subscribeToTopic("lastPhoto");
                // [END subscribe_topics]

                 // Wait  for 5 seconds before unsubscribing to make sure you received a URL
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FirebaseMessaging.getInstance().unsubscribeFromTopic("lastPhoto");
                    }
                }, 5000);


                // Log and toast
                String msg = getString(R.string.msg_subscribed);
                Log.d(TAG, msg);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });}

    @Override
    protected void onResume(){
        super.onResume();

    }
    @Override
    protected void onPause(){
        super.onPause();

    }
    public void settingsIntent(View view) {
        // Get token
        String token = FirebaseInstanceId.getInstance().getToken();        // Log and toast
        String msg = getString(R.string.msg_token_fmt, token);
        Log.d(TAG, msg);
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra(TOKEN_ID,token);
        startActivity(intent);
    }

    private class sendtoServer extends AsyncTask<String, String, String> {
        protected String doInBackground(String... strings) {
            try {
                Log.d(TAG,"sending to server");
                String settingsString = strings[0];
                String keyword = strings[1];
                String url = "http://172.31.4.188:8080/setMobileIdAndKeywords?keywords="+keyword+"&mobileId="+settingsString+"";
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                //add request header
                con.setRequestProperty("User-Agent", USER_AGENT);
                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'GET' request to URL : " + url);
                System.out.println("Response Code : " + responseCode);

            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG, e.toString());
            }
            return "done";

        }
    }



    public class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String image_url = intent.getExtras().get("message").toString();
            Log.d(TAG,image_url);
        }
    }

}

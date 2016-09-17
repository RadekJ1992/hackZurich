package com.google.firebase.quickstart.fcm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    protected void commitSettings(View view) {
        boolean violence = false;
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox_1);
        if (checkBox.isChecked()) {
            violence = true;
        }

        // send preferences to MainActivity and finish this activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("settings", "violence");
        SettingsActivity.this.startActivity(intent);
        finish();
    }

}

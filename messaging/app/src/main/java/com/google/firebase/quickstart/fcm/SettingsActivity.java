package com.google.firebase.quickstart.fcm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    // default value
    private String result = "pet";

    String[] textArray = { "Pet", "Person", "Drone", "Fire", "Waves" };
    Integer[] imageArray = { R.drawable.pet, R.drawable.person,
            R.drawable.drone, R.drawable.fire, R.drawable.waves };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout

        SpinnerAdapter adapter = new SpinnerAdapter(this, R.layout.row, textArray, imageArray);
        spinner.setAdapter(adapter);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    protected void commitSettings(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("settings", result);

        SettingsActivity.this.startActivity(intent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                result = "pet";
                break;
            case 1:
                result = "person";
                break;
            case 2:
                result = "drone";
                break;
            case 3:
                result = "fire";
                break;
            case 4:
                result = "waves";
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class SpinnerAdapter extends ArrayAdapter<String> {

        private Context ctx;
        private String[] contentArray;
        private Integer[] imageArray;

        public SpinnerAdapter(Context context, int resource, String[] objects,
                              Integer[] imageArray) {
            super(context,  R.layout.row, R.id.spinnerTextView, objects);
            this.ctx = context;
            this.contentArray = objects;
            this.imageArray = imageArray;
        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.row, parent, false);

            TextView textView = (TextView) row.findViewById(R.id.spinnerTextView);
            textView.setText(contentArray[position]);

            ImageView imageView = (ImageView)row.findViewById(R.id.spinnerImages);
            imageView.setImageResource(imageArray[position]);

            return row;
        }
    }
}

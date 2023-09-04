package com.example.tasklyaugust;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Settings extends AppCompatActivity {

    private BottomNavigationView menuBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        menuBar = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        menuBar.getMenu().getItem(3).setChecked(true);

        menuBar.setOnItemSelectedListener( item -> {

            if(item.getItemId() == R.id.home) {
                finish();
            }
            else if(item.getItemId() == R.id.calendar) {
                Intent myIntent = new Intent(Settings.this, Calendar.class);
                Settings.this.startActivity(myIntent);
                finish();

            }
            else if(item.getItemId() == R.id.profile) {
                Intent myIntent = new Intent(Settings.this, Weekly_Analysis.class);
                Settings.this.startActivity(myIntent);
                finish();
            }
            else {
                Toast.makeText(this, "Already Here",  Toast.LENGTH_SHORT).show();

            }
            return true;

        });
    }
}
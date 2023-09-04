package com.example.tasklyaugust;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Calendar extends AppCompatActivity {

    private BottomNavigationView menuBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        menuBar = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        menuBar.getMenu().getItem(1).setChecked(true);

        menuBar.setOnItemSelectedListener( item -> {

            if(item.getItemId() == R.id.home) {
                menuBar.getMenu().getItem(0).setChecked(true);
                finish();
            }
            else if(item.getItemId() == R.id.calendar) {
                Toast.makeText(this, "Already Here",  Toast.LENGTH_SHORT).show();

            }
            else if(item.getItemId() == R.id.profile) {
                Intent myIntent = new Intent(Calendar.this, Weekly_Analysis.class);
                Calendar.this.startActivity(myIntent);
                finish();

            }
            else {
                Intent myIntent = new Intent(Calendar.this, Settings.class);
                Calendar.this.startActivity(myIntent);
                finish();
            }
            return true;

        });
    }
}
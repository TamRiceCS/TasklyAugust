package com.example.tasklyaugust;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Weekly_Analysis extends AppCompatActivity {

    private BottomNavigationView menuBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_analysis);

        menuBar = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        menuBar.getMenu().getItem(2).setChecked(true);

        menuBar.setOnItemSelectedListener( item -> {

            if(item.getItemId() == R.id.home) {
                finish();
            }
            else if(item.getItemId() == R.id.calendar) {
                Intent myIntent = new Intent(Weekly_Analysis.this, Calendar.class);
                Weekly_Analysis.this.startActivity(myIntent);
                finish();
            }
            else if(item.getItemId() == R.id.stats) {
                Toast.makeText(this, "Already Here",  Toast.LENGTH_SHORT).show();

            }
            else {
                Intent myIntent = new Intent(Weekly_Analysis.this, Settings.class);
                Weekly_Analysis.this.startActivity(myIntent);
                finish();
            }
            return true;

        });
    }
}
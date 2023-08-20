package com.example.tasklyaugust;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity {

    private BottomNavigationView menuBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        menuBar = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        menuBar.setOnItemSelectedListener( item -> {

            if(item.getItemId() == R.id.home) {
                Toast.makeText(this, item.getItemId() + " 1st button",  Toast.LENGTH_SHORT).show();
            }
            else if(item.getItemId() == R.id.calendar) {
                Toast.makeText(this, item.getItemId() + " 2nd button",  Toast.LENGTH_SHORT).show();

            }
            else if(item.getItemId() == R.id.profile) {
                Toast.makeText(this, item.getItemId() + " 3rd button",  Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(this, item.getItemId() + " 4th button",  Toast.LENGTH_SHORT).show();

            }
            return true;

        });
    }
}
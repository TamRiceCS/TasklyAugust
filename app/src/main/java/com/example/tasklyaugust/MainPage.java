package com.example.tasklyaugust;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import kotlinx.coroutines.scheduling.Task;

public class MainPage extends AppCompatActivity {

    private TaskAdapter adapter;

    private BottomNavigationView menuBar;
    private FloatingActionButton add;
    private PopupWindow popUp;
    private boolean click = true;

    private String section = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // data to populate the RecyclerView with
        ArrayList<String> taskList = new ArrayList<>();
        taskList.add("Brush Teeth");
        taskList.add("Wash Face");
        taskList.add("Morning Shower");
        taskList.add("Get Dressed");
        taskList.add("Feed Cat");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.section1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskAdapter(this, taskList);
        recyclerView.setAdapter(adapter);


        menuBar = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        menuBar.getMenu().getItem(0).setChecked(true);


        menuBar.setOnItemSelectedListener( item -> {

            if(item.getItemId() == R.id.home) {
                Toast.makeText(this, "Already Here",  Toast.LENGTH_SHORT).show();
            }
            else if(item.getItemId() == R.id.calendar) {
                Intent myIntent = new Intent(MainPage.this, Calendar.class);
                MainPage.this.startActivity(myIntent);

            }
            else if(item.getItemId() == R.id.stats) {
                Intent myIntent = new Intent(MainPage.this, Weekly_Analysis.class);
                MainPage.this.startActivity(myIntent);

            }
            else {
                Intent myIntent = new Intent(MainPage.this, Settings.class);
                MainPage.this.startActivity(myIntent);
            }
            return true;

        });


        add = (FloatingActionButton) findViewById(R.id.fab);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonShowPopupWindowClick(view);
            }
        });
    }

    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.add_pop_up, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


        Spinner timeODay = (Spinner) (popupView.findViewById(R.id.spinner));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.task_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeODay.setAdapter(adapter);



        Button addTask = (Button) (popupView.findViewById(R.id.button8));
        Button cancelAdd = (Button) (popupView.findViewById(R.id.button9));


        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                section = timeODay.getSelectedItem().toString();
                Toast.makeText(MainPage.this, section,  Toast.LENGTH_SHORT).show();
            }
        });

        cancelAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                Toast.makeText(MainPage.this, "Add Task Canceled",  Toast.LENGTH_SHORT).show();
            }
        });


        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //popupWindow.dismiss();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        menuBar.getMenu().getItem(0).setChecked(true);
    }


}
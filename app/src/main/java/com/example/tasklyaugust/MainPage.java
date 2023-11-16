package com.example.tasklyaugust;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;

public class MainPage extends AppCompatActivity {

    private TaskAdapter adapterMorning;
    private RecyclerView recyclerViewMorning;

    private TaskAdapter adapterAfternoon;
    private RecyclerView recyclerViewAfternoon;

    private TaskAdapter adapterEvening;
    private RecyclerView recyclerViewEvening;

    private BottomNavigationView menuBar;
    private FloatingActionButton add;
    private PopupWindow popUp;
    private boolean click = true;

    private String section = "";
    private String addTxt = "";
    private CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // data to populate the RecyclerView with
        ArrayList<String> taskListMorn = new ArrayList<>();
        ArrayList<String> taskListAnoon = new ArrayList<>();
        ArrayList<String> taskListEven = new ArrayList<>();

        coordinatorLayout = findViewById(R.id.coordinatorLayout);


        // set up the RecyclerViews for each section
        recyclerViewMorning = findViewById(R.id.section1);
        recyclerViewMorning.setLayoutManager(new LinearLayoutManager(this));
        adapterMorning = new TaskAdapter(this, taskListMorn);
        recyclerViewMorning.setAdapter(adapterMorning);

        recyclerViewAfternoon = findViewById(R.id.section2);
        recyclerViewAfternoon.setLayoutManager(new LinearLayoutManager(this));
        adapterAfternoon = new TaskAdapter(this, taskListAnoon);
        recyclerViewAfternoon.setAdapter(adapterAfternoon);

        recyclerViewEvening = findViewById(R.id.section3);
        recyclerViewEvening.setLayoutManager(new LinearLayoutManager(this));
        adapterEvening = new TaskAdapter(this, taskListEven);
        recyclerViewEvening.setAdapter(adapterEvening);

        enableSwipeToDeleteAndUndo();



        // menu bar navigation
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

        // add button that is far right on nav bar
        add = (FloatingActionButton) findViewById(R.id.fab);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonShowPopupWindowClick(view);
            }
        });
    }

    // TODO: Find a way to make the below function more modular. (i.e is there a way to get parent RecyclerView name)
    private void enableSwipeToDeleteAndUndo() {
        SwipeDelete swipeDeleteMorn = new SwipeDelete(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                final int position = viewHolder.getAdapterPosition();
                final String item = adapterMorning.getData().get(position);

                adapterMorning.removeItem(position);

                // upon swiping an icon away, we should give safety to restore it!
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        adapterMorning.restoreItem(item, position);
                        recyclerViewMorning.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.GREEN);
                snackbar.show();

            }
        };

        SwipeDelete swipeDeleteAnoon = new SwipeDelete(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                final int position = viewHolder.getAdapterPosition();
                final String item = adapterAfternoon.getData().get(position);

                adapterAfternoon.removeItem(position);

                // upon swiping an icon away, we should give safety to restore it!
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        adapterAfternoon.restoreItem(item, position);
                        recyclerViewAfternoon.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.GREEN);
                snackbar.show();

            }
        };

        SwipeDelete swipeDeleteEven = new SwipeDelete(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                final int position = viewHolder.getAdapterPosition();
                final String item = adapterEvening.getData().get(position);

                adapterEvening.removeItem(position);

                // upon swiping an icon away, we should give safety to restore it!
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        adapterEvening.restoreItem(item, position);
                        recyclerViewEvening.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.GREEN);
                snackbar.show();

            }
        };

        ItemTouchHelper helperMorn = new ItemTouchHelper(swipeDeleteMorn);
        helperMorn.attachToRecyclerView(recyclerViewMorning);

        ItemTouchHelper helperAnoon = new ItemTouchHelper(swipeDeleteAnoon);
        helperAnoon.attachToRecyclerView(recyclerViewAfternoon);

        ItemTouchHelper helperEven = new ItemTouchHelper(swipeDeleteEven);
        helperEven.attachToRecyclerView(recyclerViewEvening);
    }

    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.add_pop_up, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        // show the popup window
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


        Spinner timeODay = (Spinner) (popupView.findViewById(R.id.spinner));

        ArrayAdapter<CharSequence> dropDown = ArrayAdapter.createFromResource(this,
                R.array.task_categories, android.R.layout.simple_spinner_item);
        dropDown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeODay.setAdapter(dropDown);



        Button addTask = (Button) (popupView.findViewById(R.id.addTask));
        Button cancelAdd = (Button) (popupView.findViewById(R.id.cancelTask));
        EditText taskDescr = (EditText) (popupView.findViewById(R.id.userTaskInput));


        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                section = timeODay.getSelectedItem().toString();
                addTxt = taskDescr.getText().toString();

                if(section.equals("Morning")) {
                    adapterMorning.addItem(addTxt);
                }

                else if(section.equals("Afternoon")) {
                    adapterAfternoon.addItem(addTxt);
                }

                else if(section.equals("Evening")) {
                    adapterEvening.addItem(addTxt);
                }

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
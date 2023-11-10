package com.example.tasklyaugust;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class NewUser extends AppCompatActivity {

    private EditText password;
    private ImageView eye;
    private int passwordToggle = 0;
    private Typeface remember;

    private Button newUserBtn;
    private Button newGoogleBtn;

    private TextView switch2SignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        // code to toggle password hidden / unhidden
        password = (EditText) findViewById(R.id.editTextTextPassword);
        eye = (ImageView) findViewById(R.id.hiddenIcon);
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passwordToggle == 0) {
                    // setInputType changes font to generic, so we have to set it back
                    remember = password.getTypeface();
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    password.setSelection(password.length());
                    passwordToggle = 1;
                    password.setTypeface(remember);
                    eye.setImageResource(R.drawable.eye_off);
                }
                else{
                    // setInputType changes font to generic, so we have to set it back
                    remember = password.getTypeface();
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password.setSelection(password.length());
                    passwordToggle = 0;
                    password.setTypeface(remember);
                    eye.setImageResource(R.drawable.eye);
                }
            }
        });

        // transfer user to main screen
        newUserBtn = (Button) findViewById(R.id.createBttn);
        Button newGoogleBtn = (Button) findViewById(R.id.signUpBttn);
        newUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(NewUser.this, MainPage.class);
                NewUser.this.startActivity(myIntent);
                finish();
            }
        });

        newGoogleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(NewUser.this, MainPage.class);
                NewUser.this.startActivity(myIntent);
                finish();
            }
        });

        switch2SignIn = (TextView) findViewById(R.id.switchSignIn);

        switch2SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(NewUser.this, ReturnUser.class);
                NewUser.this.startActivity(myIntent);
                finish();
            }
        });
    }
}
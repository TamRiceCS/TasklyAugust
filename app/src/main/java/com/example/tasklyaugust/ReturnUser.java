package com.example.tasklyaugust;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ReturnUser extends AppCompatActivity {

    private EditText password;
    private int passwordToggle = 0;
    private Typeface remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_user);

        // code to toggle password hidden / unhidden
        password = (EditText) findViewById(R.id.returnPasswordForm);

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passwordToggle == 0) {
                    // setInputType changes font to generic, so we have to set it back
                    remember = password.getTypeface();
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    password.setSelection(password.length());
                    passwordToggle = 1;
                    password.setTypeface(remember);
                    password.setCompoundDrawablesWithIntrinsicBounds( 0, 0, R.drawable.eye, 0);
                }
                else{
                    // setInputType changes font to generic, so we have to set it back
                    remember = password.getTypeface();
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password.setSelection(password.length());
                    passwordToggle = 0;
                    password.setTypeface(remember);
                    password.setCompoundDrawablesWithIntrinsicBounds( 0, 0, R.drawable.eye_off, 0);
                }
            }
        });

        // transfer user to main screen
        Button newUserBtn = (Button) findViewById(R.id.register);
        newUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ReturnUser.this, MainPage.class);
                ReturnUser.this.startActivity(myIntent);
            }
        });
    }
}
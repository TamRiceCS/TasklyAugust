package com.example.tasklyaugust;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class ReturnUser extends AppCompatActivity {

    private EditText password;
    private ImageView eye;
    private int passwordToggle = 0;
    private Typeface remember;

    private Button oldUserBtn;
    private Button oldGoogleBtn;

    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_user);

        // code to toggle password hidden / unhidden
        password = (EditText) findViewById(R.id.returnPasswordForm);
        eye = (ImageView) findViewById(R.id.eyeIcon);

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
        oldUserBtn = (Button) findViewById(R.id.register);
        oldGoogleBtn = (Button) findViewById(R.id.googleRegister);
        oldUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ReturnUser.this, MainPage.class);
                ReturnUser.this.startActivity(myIntent);
                finish();
            }
        });

        // attempt to implement google sign in w/ gso
        // TODO: finish gso for sign in

        // toggle back to sign up page
        TextView switch2SignUp = (TextView) findViewById(R.id.switchSignUp);

        switch2SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ReturnUser.this, NewUser.class);
                ReturnUser.this.startActivity(myIntent);
                finish();
            }
        });

    }
}
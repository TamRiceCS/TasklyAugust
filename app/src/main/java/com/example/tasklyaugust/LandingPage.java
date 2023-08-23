package com.example.tasklyaugust;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class LandingPage extends AppCompatActivity {

    private VideoView tasklyVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        // grab video from raw file and grab container it will go in
        tasklyVideo = findViewById(R.id.backgroundVid);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.taskly_landing);
        tasklyVideo.setVideoURI(uri);
        tasklyVideo.start();

        // figure out how to make below, a lambda func to get rid of warning
        tasklyVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });

        Button newUserBtn = (Button) findViewById(R.id.newUserBtn);
        newUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LandingPage.this, NewUser.class);
                LandingPage.this.startActivity(myIntent);
                finish(); // landing page is done
            }
        });

        // open ReturningUser activity using btn
        Button returnUserBtn = (Button) findViewById(R.id.returnUserBtn);
        returnUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LandingPage.this, ReturnUser.class);
                LandingPage.this.startActivity(myIntent);
                finish(); // landing page is done
            }
        });

    }

    // if the user comes back, the video will start again
    @Override
    protected void onResume() {
        super.onResume();
        // to restart the video after coming from other activity like Sing up
        tasklyVideo.start();
    }
}
package com.example.tasklyaugust;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView tasklyVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // work w/ background video
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

    }

    // if the user comes back, the video will start again
    @Override
    protected void onResume() {
        super.onResume();
        // to restart the video after coming from other activity like Sing up
        tasklyVideo.start();
    }
}
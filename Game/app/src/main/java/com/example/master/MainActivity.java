/* @author : Anastasia Alexia Capo-chichi */

package com.example.master;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    // the play button
    private ImageButton buttonPlay;

    // the high score button
    private ImageButton buttonScore;

    // the about button
    private ImageButton buttonAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initializing the play button
        buttonPlay = (ImageButton) findViewById(R.id.btnPlay);

        //initializing the highscore button
        buttonScore = (ImageButton) findViewById(R.id.btnHighScore);

        //initializing the about button
        buttonAbout = (ImageButton) findViewById(R.id.btnAbout);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GameActivity.class)); //start an activity on GameActivity

            }
        });

        buttonScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HighScore.class));
            }
        });


    }
}


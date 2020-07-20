package com.example.master;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HighScore extends AppCompatActivity {

    TextView one, two, three, four, five;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_highscore);

        one = (TextView) findViewById(R.id.textView);
        two = (TextView) findViewById(R.id.textView2);
        three = (TextView) findViewById(R.id.textView3);
        four = (TextView) findViewById(R.id.textView4);

        sharedPreferences = getSharedPreferences("game", Context.MODE_PRIVATE);
        one.setText("1. "+sharedPreferences.getInt("score1",0));
        two.setText("2. "+sharedPreferences.getInt("score2",0));
        three.setText("3. "+sharedPreferences.getInt("score3",0));
        four.setText("4. "+sharedPreferences.getInt("score4",0));


    }


}

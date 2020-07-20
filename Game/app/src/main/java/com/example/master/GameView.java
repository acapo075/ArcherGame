package com.example.master;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Display;
import android.view.SurfaceView;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {

    //instances variables

    private boolean isPlaying, isGameOver;


    private int screenX, screenY;
    private int i = 0, score;

    private int highScore[] = new int[4];

    public static float screenRatioX, screenRatioY;

    private Thread thread;
    private Paint paint;
    private Background background1, background2;
    private Character character;
    private Golem golem;
    private SharedPreferences sharedPreferences;
    private Context context;
    private Resources resources;
    private GameActivity gameActivity;

    private Bitmap gameOver;


    private List<Arrow> arrows;





    //constructor
     public GameView(GameActivity gameActivity, int screenX, int screenY) {



        super(gameActivity);

        this.gameActivity = gameActivity;

        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());
        background2.x = screenX;

        this.screenX = screenX;
        this.screenY = screenY;

        screenRatioX = 2000f / screenX;
        screenRatioY = 1100f / screenY;

        isGameOver = false;

        isPlaying = true;


        paint = new Paint();

        character = new Character(this, screenY, getResources());
        golem = new Golem(this, screenX, screenY, getResources());

        arrows = new ArrayList<>();

        sharedPreferences = gameActivity.getSharedPreferences("game", Context.MODE_PRIVATE);

        score = 0;



        highScore[0] = sharedPreferences.getInt("score1",0);
        highScore[1] = sharedPreferences.getInt("score2",0);
        highScore[2] = sharedPreferences.getInt("score3",0);
        highScore[3] = sharedPreferences.getInt("score4",0);

        gameOver = BitmapFactory.decodeResource(resources, R.drawable.gameover);


    }

    //resume the game
    public void resume() {

        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    //run the game
    public void run() {
        while (isPlaying) {
            update();
            draw();
            sleep();
        }
    }

    //update the game
    private void update() {
        background1.x -= 30 * screenRatioX;
        background2.x -= 30 * screenRatioX;

        if (background1.x + background1.background.getWidth() < 0) {
            background1.x = screenX;
        }

        if (background2.x + background2.background.getWidth() < 0) {
            background2.x = screenX;
        }

        if (character.y < 0) {
            character.y = 0;
        }

        if(golem.y < 0) {
            golem.y = 0;
        }

        if (character.y >= screenY - character.height) {
            character.y = screenY - character.height - 20;
            i = 0;
        }

        if(golem.y >= screenY - golem.height) {
            golem.y = screenY - golem.height - 75;
        }

        if(character.isJump) {
            character.y -= 100 * screenRatioY;
        } else {
            character.y += 30 * screenRatioY;
        }


        if (character.y == screenY - character.height - 20) {
            i = 0;
        }

        //let the golem go foward
        golem.x -= 20;

        List<Arrow> delete = new ArrayList<>();

        for (Arrow arrow : arrows) {

            if (arrow.getX() > screenX) {
                delete.add(arrow);
            }

            arrow.setX((int) (arrow.getX() + 55 * screenRatioX));

            if (Rect.intersects(arrow.getCollision(), golem.getCollision())) {
                score++;
                golem.isShoot = true;
                arrow.setX(screenX + 900);
                golem.x = screenX + 900;

            }
        }

        for (Arrow arrow : delete)
            arrows.remove(arrow);

        if(Rect.intersects(golem.getCollision(), character.getCollision())) {

            character.dead = 1;
            isGameOver = true;
            return;


        }



        }



    //draw the PNG image on the user interface
    private void draw() {

        if (getHolder().getSurface().isValid()) {

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);


            int yPos=(int) ((canvas.getHeight()) - ((paint.descent() + paint.ascent())));
            paint.setTextSize(150);
            paint.setTextAlign(Paint.Align.CENTER);

            canvas.drawText(score + "", canvas.getWidth()/2,yPos,paint);


            canvas.drawBitmap(character.getRun(), character.x, character.y, paint);

            for (Arrow arrow : arrows) {
                canvas.drawBitmap(arrow.arrow, arrow.getX(), arrow.getY(), paint);
            }


            canvas.drawBitmap(golem.getRun(), golem.x, golem.y, paint);

            if(isGameOver) {


                isPlaying = false;
                waitBeforeExiting();
                saveHighScore();

            }

            getHolder().unlockCanvasAndPost(canvas);
        }

    }

    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void pause() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_UP:
                if (event.getX() > screenX / 2) {
                    character.attack++;
                    break;
                }
            case MotionEvent.ACTION_DOWN :
                if(i < 2) {
                    if (event.getX() < screenX / 2) {
                        character.jump++;
                        character.isJump = true;
                        i++;
                        break;
                    }
                }
        }


        return true;

    }

    public void saveHighScore() {

        for(int i = 0; i < 4; i++) {

            if (highScore[i] < score) {

                final int finalI = i;
                highScore[i] = score;

            }
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();

        for(int i = 0; i<4; i++) {
            int j = i + 1;
            editor.putInt("score"+j, highScore[i]);
        }
        editor.apply();


        }

    private void waitBeforeExiting() {

        try {
            Thread.sleep(3000);
            gameActivity.startActivity(new Intent(gameActivity, MainActivity.class));
            gameActivity.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    //create a new arrow to display
    public void newArrow() {

        Arrow arrow = new Arrow((getResources()));

        arrow.setX(character.x + (character.width / 2 ));
        arrow.setY(character.y + (character.height / 3));
        arrows.add(arrow);

    }
}


package com.example.master;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.view.SurfaceView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;

import android.graphics.Paint;
import android.graphics.Rect;

import android.view.MotionEvent;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {

    //instances variables

    private boolean isPlaying, isGameOver;
    private boolean isGolem, isWitch;


    private int screenX, screenY;
    private int i = 0, score, time;
    private int maxArrow, arrowCounter;

    private int highScore[] = new int[4];

    public static float screenRatioX, screenRatioY;

    private Thread thread;
    private Paint paint;
    private Background background1, background2;
    private Character character;
    private SharedPreferences sharedPreferences;
    private Context context;
    private Resources resources;
    private GameActivity gameActivity;

    private Bitmap gameOver;

    private Golem golems[];
    private Witch witchs[];

    private List<Arrow> arrows;
    private List<Bonus> bonuses;

    private Random generator;



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

        arrows = new ArrayList<>();

        maxArrow = 10;
        arrowCounter = 10;

        sharedPreferences = gameActivity.getSharedPreferences("game", Context.MODE_PRIVATE);

        score = 0;
        time = 0;

        bonuses = new ArrayList<>();

        golems = new Golem[5];
        witchs = new Witch[5];

        for(int i = 0; i < 5; i++) {
            golems[i] =  new Golem(this, screenX, screenY, getResources());
            witchs[i] = new Witch(this, screenX, screenY, getResources());
        }



        highScore[0] = sharedPreferences.getInt("score1",0);
        highScore[1] = sharedPreferences.getInt("score2",0);
        highScore[2] = sharedPreferences.getInt("score3",0);
        highScore[3] = sharedPreferences.getInt("score4",0);

        gameOver = BitmapFactory.decodeResource(resources, R.drawable.gameover);


        generator = new Random();

       




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

         //increamente the time
         time++;

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


        if (character.y >= screenY - character.height) {
            character.y = screenY - character.height - 20;
            i = 0;
        }


        if(character.isJump) {
            character.y -= 100 * screenRatioY;
        } else {
            character.y += 30 * screenRatioY;
        }

        if (character.y == screenY - character.height - 20) {
            i = 0;
        }

       List<Bonus> del = new ArrayList<>();

        if(time%40 == 0 ) {
            newBonus();
        }

        for(Bonus bonus : bonuses ) {

            bonus.x -= 30 * screenRatioX;


            if(Rect.intersects(bonus.getCollision(), character.getCollision())) {

                arrowCounter++;
                bonus.x = screenX + 900;
            }

            if(bonus.x > screenX ) {
                del.add(bonus);
            }
        }



        List<Arrow> delete = new ArrayList<>();

        for(Golem golem : golems ) {
            if(golem.y >= screenY - golem.height) {
                golem.y = screenY - golem.height - 75;
            }

            if(golem.y < 0) {
                golem.y = 0;
            }
            golem.x -= 10;

            if(Rect.intersects(golem.getCollision(), character.getCollision()) ) {

                character.dead = 1;
                isGameOver = true;
                return;

            }
        }

        for( Witch witch : witchs ) {
            witch.x -= 20;

            if(witch.y < 0 ) {
                witch.y = 0;
            }

            if(witch.y >= screenY - witch.height) {
                witch.y = screenY - witch.height - 100;
            }
            if (Rect.intersects(witch.getCollision(), character.getCollision())) {

                character.dead = 1;
                isGameOver = true;
                return;
            }
        }


        for (Arrow arrow : arrows) {

            if (arrow.getX() > screenX) {
                delete.add(arrow);
            }

            arrow.setX((int) (arrow.getX() + 55 * screenRatioX));

            for(Golem golem : golems ) {

                if (Rect.intersects(arrow.getCollision(), golem.getCollision())) {
                    score++;
                    golem.isShoot = true;
                    golem.isDead = true;
                    arrow.setX(screenX + 900);
                    golem.x = screenX + generator.nextInt(1800) + 900 ;
                    isGolem = false;

                }
            }

            for(Witch witch : witchs ) {

                if(Rect.intersects(arrow.getCollision(), witch.getCollision())) {
                    score++;
                    witch.isShoot = true;
                    witch.isDead = true;
                    arrow.setX(screenX + 900);
                    witch.x = screenX + generator.nextInt(1800) + 900;
                    isWitch = false;
                }
            }


        }

        for (Arrow arrow : delete) {
            arrows.remove(arrow);
        }

        for(Bonus bonus : del) {
            bonuses.remove(bonus);
        }
     }



    //draw the PNG image on the user interface
    private void draw() {

        if (getHolder().getSurface().isValid()) {

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);

            if(!isGameOver) {

                int yPos=(int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 3));
                paint.setTextSize(150);
                paint.setTextAlign(Paint.Align.LEFT);

                canvas.drawText("Score: " +score + "", canvas.getWidth()/2,yPos,paint);

                paint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText("Arrow: " + arrowCounter + " ", canvas.getWidth() / 2, yPos, paint);
            }


            canvas.drawBitmap(character.getRun(), character.x, character.y, paint);

            for (Arrow arrow : arrows) {
                canvas.drawBitmap(arrow.arrow, arrow.getX(), arrow.getY(), paint);
            }

            for (Bonus bonus : bonuses) {
                canvas.drawBitmap(bonus.arrow, bonus.x, bonus.y, paint);
            }

            for(Golem golem : golems ) {

                canvas.drawBitmap(golem.getRun(), golem.x, golem.y, paint);
            }

            for(Witch witch : witchs ) {

                canvas.drawBitmap(witch.getRun(), witch.x, witch.y, paint);
            }


            if(isGameOver) {

                int y=(int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 3));
                paint.setTextSize(250);
                paint.setTextAlign(Paint.Align.CENTER);

                canvas.drawText("GAME OVER" , canvas.getWidth()/2,y,paint);

                isPlaying = false;
                exiting();
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

    //on click
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_UP:
                if(arrowCounter > 0 ) {
                    if (event.getX() > screenX / 2 ) { //if the user touch the screen on the right
                        character.attack++;
                        break;
                    }
                }

            case MotionEvent.ACTION_DOWN :
                if(i < 2) {
                    if (event.getX() < screenX / 2) { //if the user touch the screen on the left
                        character.jump++;
                        character.isJump = true;
                        i++;
                        break;
                    }
                }
        }


        return true;

    }



    //save the high score
    public void saveHighScore() {

        for(int i = 3; i >= 0; i--) {

            if (highScore[i] < score ) {

                highScore[i] = score;
                break;

            }

        }

        SharedPreferences.Editor editor = sharedPreferences.edit();


        for(int i = 0; i < 4; i++) {

            editor.putInt("score"+ (i+1), highScore[i]);
        }


        editor.apply();


        }

        //go back to the main screen view
    private void exiting() {

        try {
            Thread.sleep(5000);
            gameActivity.startActivity(new Intent(gameActivity, MainActivity.class));
            gameActivity.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    //create a new arrow to display
    public void newArrow() {

         if(arrowCounter != 0 ) {

             arrowCounter--;

            Arrow arrow = new Arrow((getResources()));

             arrow.setX(character.x + (character.width / 2 ));
             arrow.setY(character.y + (character.height / 3));

             arrows.add(arrow);

         }


    }

    //to create new bonus
    public void newBonus() {

         Bonus bonus = new Bonus(screenX, screenY, getResources());

         bonuses.add(bonus);

    }
}


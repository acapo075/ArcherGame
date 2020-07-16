package com.example.master;

import android.content.Context;
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

    private boolean isPlaying;

    private int screenX, screenY;

    public static float screenRatioX, screenRatioY;

    private Thread thread;
    private Paint paint;
    private Background background1, background2;
    private Character character;

    private List<Arrow> arrows;

    //constructor
     public GameView(Context context, int screenX, int screenY) {

        super(context);

        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());
        background2.x = screenX;

        this.screenX = screenX;
        this.screenY = screenY;

        screenRatioX = 2000f / screenX;
        screenRatioY = 1100f / screenY;


        paint = new Paint();

        character = new Character(this, screenY, getResources());

        arrows = new ArrayList<>();
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
        background1.x -= 28 * screenRatioX;
        background2.x -= 28 * screenRatioX;

        if (background1.x + background1.background.getWidth() < 0) {
            background1.x = screenX;
        }

        if (background2.x + background2.background.getWidth() < 0) {
            background2.x = screenX;

        }

        if (character.y < 0) {
            character.y = -1;
        }

        if (character.y >= screenY - character.height) {
            character.y = screenY - character.height;
        }

        if(character.isJump == true ) {
            character.y -=
        } else {
            character.y +=4
        }

        List<Arrow> delete = new ArrayList<>();

        for (Arrow arrow : arrows) {

            if (arrow.getX() > screenX)
                delete.add(arrow);

            arrow.setX((int) (arrow.getX() + 55 * screenRatioX));
        }

        for (Arrow arrow : delete)
            arrows.remove(arrow);

    }

    //draw the PNG image on the user interface
    private void draw() {

        if (getHolder().getSurface().isValid()) {

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);



            canvas.drawBitmap(character.getRun(), character.x, character.y, paint);

            for (Arrow arrow : arrows)
                canvas.drawBitmap(arrow.arrow, arrow.getX(), arrow.getY(), paint);

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
                if (event.getX() < screenX / 2) {
                    character.isJump = true;
                    break;
                }
        }

        return true;

    }

    //create a new arrow to display
    public void newArrow() {

        Arrow arrow = new Arrow((getResources()));

        arrow.setX(character.x + (character.width / 2 ));
        arrow.setY(character.y + (character.height / 3));
        arrows.add(arrow);

    }
}


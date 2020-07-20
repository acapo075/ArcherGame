package com.example.master;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.master.GameView.screenRatioX;
import static com.example.master.GameView.screenRatioY;

public class Golem {

    //instances variables


    int run = 0, dead = 0;
    int x, y, width, height, runCounter = 1, deadCounter;

    boolean isShoot, isDead;

    Bitmap run1, run2, run3, run4, run5, run6, run7;
    Bitmap dead1, dead2, dead3, dead4, dead5, dead6, dead7, dead8, dead9, dead10, dead11, dead12, dead13;


    GameView gameView;

    //constructor

    public Golem(GameView gameView, int screenX, int screenY, Resources res) {

        this.gameView = gameView;

        run1 = BitmapFactory.decodeResource(res, R.drawable.golemrun1);
        run2 = BitmapFactory.decodeResource(res, R.drawable.golemrun2);
        run3 = BitmapFactory.decodeResource(res, R.drawable.golemrun3);
        run4 = BitmapFactory.decodeResource(res, R.drawable.golemrun4);
        run5 = BitmapFactory.decodeResource(res, R.drawable.golemrun5);
        run6 = BitmapFactory.decodeResource(res, R.drawable.golemrun6);
        run7 = BitmapFactory.decodeResource(res, R.drawable.golemrun7);


        //        dead1 = BitmapFactory.decodeResource(res, R.drawable.dead1);
//        dead2 = BitmapFactory.decodeResource(res, R.drawable.dead2);
//        dead3 = BitmapFactory.decodeResource(res, R.drawable.dead3);
//        dead4 = BitmapFactory.decodeResource(res, R.drawable.dead4);
//        dead5 = BitmapFactory.decodeResource(res, R.drawable.dead5);
//        dead6 = BitmapFactory.decodeResource(res, R.drawable.dead6);
//        dead7 = BitmapFactory.decodeResource(res, R.drawable.dead7);
//        dead8 = BitmapFactory.decodeResource(res, R.drawable.dead8);

        width = run1.getWidth();
        height = run1.getHeight();

        width *= 3;
        height *= 2;

        width = (int) (screenRatioX * width);
        height = (int) (screenRatioY * height);

        run1 = Bitmap.createScaledBitmap(run1, width, height, false);
        run2 = Bitmap.createScaledBitmap(run2, width, height, false);
        run3 = Bitmap.createScaledBitmap(run3, width, height, false);
        run4 = Bitmap.createScaledBitmap(run4, width, height, false);
        run5 = Bitmap.createScaledBitmap(run5, width, height, false);
        run6 = Bitmap.createScaledBitmap(run6, width, height, false);
        run7 = Bitmap.createScaledBitmap(run7, width, height, false);


//        dead1 = Bitmap.createScaledBitmap(dead1, width, height, false);
//        dead2 = Bitmap.createScaledBitmap(dead2, width, height, false);
//        dead3 = Bitmap.createScaledBitmap(dead3, width, height, false);
//        dead4 = Bitmap.createScaledBitmap(dead4, width, height, false);
//        dead5 = Bitmap.createScaledBitmap(dead5, width, height, false);
//        dead6 = Bitmap.createScaledBitmap(dead6, width, height, false);
//        dead7 = Bitmap.createScaledBitmap(dead7, width, height, false);

        y = screenY;
        x = screenX;


    }

    //make the golem run and dead.
    /*@return: Bitmap*/
    public Bitmap getRun() {

        if(dead != 0) {
            run = 1;
            if(deadCounter == 1) {
                deadCounter++;
                return dead1;
            }
            if(deadCounter == 2) {
                deadCounter++;
                return dead2;
            }
            if(deadCounter == 3) {
                deadCounter++;
                return dead3;
            }
            if(deadCounter == 4) {
                deadCounter++;
                return dead4;
            }
            if(deadCounter == 5) {
                deadCounter++;
                return dead5;
            }
            if(deadCounter == 6) {
                deadCounter++;
                return dead6;
            }
            if(deadCounter == 7) {
                deadCounter++;
                return dead7;
            }
            if(deadCounter == 8) {
                deadCounter++;
                return dead8;
            }
            if(deadCounter == 9) {
                deadCounter++;
                return dead9;
            }
            if(deadCounter == 10) {
                deadCounter++;
                return dead10;
            }
            isDead = true;
            return dead11;
        }


        if (run == 0) {
            if (runCounter == 1) {
                runCounter++;
                return run1;
            }

            if (runCounter == 2) {
                runCounter++;
                return run2;
            }
            if (runCounter == 3) {
                runCounter++;
                return run3;

            }
            if (runCounter == 4) {
                runCounter++;
                return run4;

            }
            if (runCounter == 5) {
                runCounter++;
                return run5;
            }
            if (runCounter == 6) {
                runCounter++;
                return run6;
            }

        }
        run = 0;
        runCounter = 1;
        return run7;
    }

    //if the golem touch something
    public Rect getCollision() {

        Rect collision = new Rect(x, y, x + width, y + height);

        return collision;
    }

}

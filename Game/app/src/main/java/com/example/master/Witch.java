package com.example.master;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import java.util.Random;

import static com.example.master.GameView.screenRatioX;
import static com.example.master.GameView.screenRatioY;

public class Witch  {

    //instances variables
    int run = 0, dead = 0;
    int x, y, width, height, runCounter = 1, deadCounter;

    boolean isShoot, isDead;

    Bitmap run1, run2, run3, run4, run5, run6, run7, run8;
    Bitmap dead1, dead2, dead3, dead4, dead5, dead6, dead7, dead8, dead9, dead10, dead11, dead12, dead13;


    GameView gameView;

    Random generator;

    //constructor
    public Witch(GameView gameView, int screenX, int screenY, Resources res) {

        this.gameView = gameView;

        //to make witch with random position
        generator = new Random();

        //get the image for the run
        run1 = BitmapFactory.decodeResource(res, R.drawable.witchrun1);
        run2 = BitmapFactory.decodeResource(res, R.drawable.witchrun2);
        run3 = BitmapFactory.decodeResource(res, R.drawable.witchrun3);
        run4 = BitmapFactory.decodeResource(res, R.drawable.witchrun4);
        run5 = BitmapFactory.decodeResource(res, R.drawable.witchrun5);
        run6 = BitmapFactory.decodeResource(res, R.drawable.witchrun6);
        run7 = BitmapFactory.decodeResource(res, R.drawable.witchrun7);
        run8 = BitmapFactory.decodeResource(res, R.drawable.witchrun8);

        //initializing the width and the height with the width and the height of the first image
        width = run1.getWidth();
        height = run1.getHeight();

        //give the appropriate height and width for the game
        width *= 3;
        height *= 2;

        //synchronize the width and the height with the background
        width = (int) (height * screenRatioX);
        height = (int) (width * screenRatioY);

        //give the same width and the same height for all the image
        run1 = Bitmap.createScaledBitmap(run1, width, height, false);
        run2 = Bitmap.createScaledBitmap(run2, width, height, false);
        run3 = Bitmap.createScaledBitmap(run3, width, height, false);
        run4 = Bitmap.createScaledBitmap(run4, width, height, false);
        run5 = Bitmap.createScaledBitmap(run5, width, height, false);
        run6 = Bitmap.createScaledBitmap(run6, width, height, false);
        run7 = Bitmap.createScaledBitmap(run7, width, height, false);
        run8 = Bitmap.createScaledBitmap(run8, width, height, false);

        //give a random coordinates for the witch
        y = screenY;
        x = generator.nextInt(screenX) + 1000 ;

        isDead = false;

    }

    //make the witch run, attack, jump and dead.
    /*@return: the bitmap*/

    public Bitmap getRun() {

        if(run == 0) {
            if(runCounter == 1) {
                runCounter++;
                return run1;
            }

            if(runCounter == 2) {
                runCounter++;
                return run2;
            }
            if(runCounter == 3) {
                runCounter++;
                return run3;

            }
            if(runCounter == 4) {
                runCounter++;
                return run4;

            }
            if(runCounter == 5) {
                runCounter++;
                return run5;
            }
            if(runCounter == 6) {
                runCounter++;
                return run6;
            }
            if(runCounter == 7) {
                runCounter++;
                return run7;
            }

        }
        run = 0;
        runCounter = 1;
        return run8;
    }

    public Rect getCollision() {

        Rect collision = new Rect(x, y, x + width, y + height);

        return collision;
    }
}

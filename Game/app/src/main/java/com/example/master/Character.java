/* @author : Anastasia Alexia Capo-chichi  */

package com.example.master;

import android.graphics.Bitmap;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.master.GameView.screenRatioX;
import static com.example.master.GameView.screenRatioY;


/* This class implement the object character.
* Character can run, attack and jump */


public class Character {


    //instances variables

    int attack = 0, run = 0, jump = 0;
    int x, y, width, height, runCounter = 1, attackCounter, jumpCounter;

    boolean isJump = false, isDead = false;

    Bitmap run1, run2, run3, run4, run5, run6, run7, run8;
    Bitmap attack1, attack2, attack3, attack4, attack5, attack6, attack7, attack8, attack9, attack10;
    Bitmap jump1, jump2, jump3, jump4, jump5, jump6;
    Bitmap dead1, dead2, dead3, dead4, dead5, dead6, dead7, dead8, dead9, dead10;

    private GameView gameView;



    //Constructor
    public Character(GameView gameView, int screenY, Resources res) {

        this.gameView = gameView;

        //get the image for the run
        run1 = BitmapFactory.decodeResource(res, R.drawable.run1);
        run2 = BitmapFactory.decodeResource(res, R.drawable.run2);
        run3 = BitmapFactory.decodeResource(res, R.drawable.run3);
        run4 = BitmapFactory.decodeResource(res, R.drawable.run4);
        run5 = BitmapFactory.decodeResource(res, R.drawable.run5);
        run6 = BitmapFactory.decodeResource(res, R.drawable.run6);
        run7 = BitmapFactory.decodeResource(res, R.drawable.run7);
        run8 = BitmapFactory.decodeResource(res, R.drawable.run8);

        //get the image for the attack
        attack1 = BitmapFactory.decodeResource(res, R.drawable.attack1);
        attack2 = BitmapFactory.decodeResource(res, R.drawable.attack2);
        attack3 = BitmapFactory.decodeResource(res, R.drawable.attack3);
        attack4 = BitmapFactory.decodeResource(res, R.drawable.attack4);
        attack5 = BitmapFactory.decodeResource(res, R.drawable.attack5);
        attack6 = BitmapFactory.decodeResource(res, R.drawable.attack6);
        attack7 = BitmapFactory.decodeResource(res, R.drawable.attack7);
        attack8 = BitmapFactory.decodeResource(res, R.drawable.attack8);
        attack9 = BitmapFactory.decodeResource(res, R.drawable.attack9);
        attack10 = BitmapFactory.decodeResource(res, R.drawable.attack10);

        jump1 = BitmapFactory.decodeResource(res, R.drawable.jump1);
        jump2 = BitmapFactory.decodeResource(res, R.drawable.jump2);
        jump3 = BitmapFactory.decodeResource(res, R.drawable.jump3);
        jump4 = BitmapFactory.decodeResource(res, R.drawable.jump4);
        jump5 = BitmapFactory.decodeResource(res, R.drawable.jump5);
        jump6 = BitmapFactory.decodeResource(res, R.drawable.jump6);


        dead1 = BitmapFactory.decodeResource(res, R.drawable.dead1);
        dead2 = BitmapFactory.decodeResource(res, R.drawable.dead2);
        dead3 = BitmapFactory.decodeResource(res, R.drawable.dead3);
        dead4 = BitmapFactory.decodeResource(res, R.drawable.dead4);
        dead5 = BitmapFactory.decodeResource(res, R.drawable.dead5);
        dead6 = BitmapFactory.decodeResource(res, R.drawable.dead6);
        dead7 = BitmapFactory.decodeResource(res, R.drawable.dead7);
        dead8 = BitmapFactory.decodeResource(res, R.drawable.dead8);





        //initializing the width and the height with the width and the height of the first image
        width = run1.getWidth();
        height = run1.getHeight();

        //give the appropriate height and width for the game
        width *= 3;
        height *= 2;


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

        attack1 = Bitmap.createScaledBitmap(attack1, width, height, false);
        attack2 = Bitmap.createScaledBitmap(attack2, width, height, false);
        attack3 = Bitmap.createScaledBitmap(attack3, width, height, false);
        attack4 = Bitmap.createScaledBitmap(attack4, width, height, false);
        attack5 = Bitmap.createScaledBitmap(attack5, width, height, false);
        attack6 = Bitmap.createScaledBitmap(attack6, width, height, false);
        attack7 = Bitmap.createScaledBitmap(attack7, width, height, false);
        attack8 = Bitmap.createScaledBitmap(attack8, width, height, false);
        attack9 = Bitmap.createScaledBitmap(attack9, width, height, false);
        attack10 = Bitmap.createScaledBitmap(attack10, width, height, false);

        jump1 = Bitmap.createScaledBitmap(jump1, width, height, false);
        jump2 = Bitmap.createScaledBitmap(jump2, width, height, false);
        jump3 = Bitmap.createScaledBitmap(jump3, width, height, false);
        jump4 = Bitmap.createScaledBitmap(jump4, width, height, false);
        jump5 = Bitmap.createScaledBitmap(jump5, width, height, false);
        jump6 = Bitmap.createScaledBitmap(jump6, width, height, false);

        dead1 = Bitmap.createScaledBitmap(dead1, width, height, false);
        dead2 = Bitmap.createScaledBitmap(dead2, width, height, false);
        dead3 = Bitmap.createScaledBitmap(dead3, width, height, false);
        dead4 = Bitmap.createScaledBitmap(dead4, width, height, false);
        dead5 = Bitmap.createScaledBitmap(dead5, width, height, false);
        dead6 = Bitmap.createScaledBitmap(dead6, width, height, false);
        dead7 = Bitmap.createScaledBitmap(dead7, width, height, false);





        //give a coordinates for character
        y = screenY;
        x = (int) (screenRatioX / 100);

    }

    //make the character run, attack and jump.
    /*@return: the bitmap*/
    public Bitmap getRun() {

        if(jump != 0 ) {
            if(jumpCounter == 1 ) {
                jumpCounter++;
                return jump1;
            }
            if(jumpCounter == 2 ) {
                jumpCounter++;
                return jump2;
            }
            if(jumpCounter == 3 ) {
                jumpCounter++;
                return jump3;
            }
            if(jumpCounter == 4 ) {
                jumpCounter++;
                return jump4;
            }
            if(jumpCounter == 5 ) {
                jumpCounter++;
                return jump5;
            }
            jumpCounter = 1;
            jump--;
            return jump6;
        }

        if(attack != 0 ) {
            if(attackCounter == 1 ) {
                attackCounter++;
                return attack1;
            }
            if(attackCounter == 2 ) {
                attackCounter++;
                return attack2;
            }
            if(attackCounter == 3 ) {
                attackCounter++;
                return attack3;
            }
            if(attackCounter == 4 ) {
                attackCounter++;
                return attack4;
            }
            if(attackCounter == 5 ) {
                attackCounter++;
                return attack5;

            }
            if(attackCounter == 6 ) {
                attackCounter++;
                return attack6;
            }
            if(attackCounter == 7 ) {
                attackCounter++;
                return attack7;
            }
            if(attackCounter == 8 ) {
                attackCounter++;
                return attack8;
            }
            if(attackCounter == 9 ) {
                attackCounter++;
                return attack9;
            }

            attackCounter = 1;
            attack--;
            gameView.newArrow();
            return attack10;

        }
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


}

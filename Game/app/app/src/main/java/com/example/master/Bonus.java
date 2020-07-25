package com.example.master;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;


/*author : Alexia Anastasia Capo-chichi */

import static com.example.master.GameView.screenRatioX;
import static com.example.master.GameView.screenRatioY;

public class Bonus {

    //instances variables

    int height, width;
    int x, y;


    Bitmap arrow;


    Random generator;



    //constructor
    public Bonus(int screenX, int screenY, Resources resources) {


        generator = new Random();

        arrow = BitmapFactory.decodeResource(resources, R.drawable.arrow1);

        width = arrow.getWidth();
        height = arrow.getHeight();

        width *= 3;
        height *= 2;

        width = (int) (height * screenRatioX);
        height = (int) (width * screenRatioY);

        arrow = Bitmap.createScaledBitmap(arrow, width, height, false);

        x = generator.nextInt(screenX);
        y = generator.nextInt(screenY);
    }


    //if the bonus is touch something
    public Rect getCollision() {

        Rect collision = new Rect(x, y, x + width, y + height);

        return collision;
    }
}

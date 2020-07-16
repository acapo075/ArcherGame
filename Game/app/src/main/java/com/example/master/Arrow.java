/* @author : Anastasia Alexia Capo-chichi  */
package com.example.master;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.master.GameView.screenRatioX;
import static com.example.master.GameView.screenRatioY;

public class Arrow {

    //instances variables

    private int x, y, width, height;

    Bitmap arrow;

    //getters

    public int getX() {
        return x;
    }

    public int getY() {

        return y;
    }
    public int getWidth() {

        return width;
    }
    public int getHeight() {

        return height;
    }

    //setters

    public int setX(int nx) {
        x = nx;
        return x;
    }
    public int setY(int ny) {
        y = ny;
        return y;
    }

    //constructor
   public Arrow(Resources resources) {

        arrow = BitmapFactory.decodeResource(resources, R.drawable.arrow1);

        width = arrow.getWidth();
        height = arrow.getHeight();

        width *= 3;
        height *= 2;

        width = (int) (height * screenRatioX);
        height = (int) (width * screenRatioY);

        arrow = Bitmap.createScaledBitmap(arrow, width, height, false);
    }


    //if the arrow touch something
    public Rect getCollision() {

       Rect collision = new Rect(x, y, x + width, y + height);

        return collision;
    }
}

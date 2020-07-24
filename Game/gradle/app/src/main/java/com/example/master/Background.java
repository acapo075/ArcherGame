
/* @author : Anastasia Alexia Capo-chichi */

package com.example.master;

import android.content.res.Resources;
import  android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Background { // movable background


    int x = 0, y = 0;

    Bitmap background;

    //constructor
    public Background(int sX, int sY, Resources resources) {

        background = BitmapFactory.decodeResource(resources, R.drawable.background2); //get the PNG image

        background = Bitmap.createScaledBitmap(background, sX, sY, false); //scale the background with SX and SY
    }
}


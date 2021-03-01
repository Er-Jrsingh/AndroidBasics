package com.androidbasics;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;

public class ScreenUtils {

    public float vHeight;
    public float hWidth;

    public ScreenUtils(Activity activity){
        Display display=activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMatrix=new DisplayMetrics();

        display.getMetrics(outMatrix);
        float density=activity.getResources().getDisplayMetrics().density;

        vHeight=outMatrix.heightPixels/density;
        hWidth=outMatrix.widthPixels/density;
    }
    public float getVerticalHeight(){ return vHeight;}
    public float getHorizontalWidth(){ return hWidth;}
}

package com.example.jariwher.sunshine.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jariwher on 10/28/2015.
 */
public class MyView extends View{
    private int myHeight;
    private int myWidth;
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int hSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSpecSize = MeasureSpec.getMode(heightMeasureSpec);
            myHeight = hSpecSize;

        if (hSpecMode == MeasureSpec.EXACTLY)
            myHeight = hSpecSize;
        else if (hSpecMode == MeasureSpec.AT_MOST);
            //Wrap Content

        int wSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSpecSize = MeasureSpec.getMode(widthMeasureSpec);
            myWidth = wSpecSize;

        if (wSpecMode == MeasureSpec.EXACTLY)
            myWidth = hSpecSize;
        else if (hSpecMode == MeasureSpec.AT_MOST);
        //Wrap Content
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int radius = 100;
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        int strokeWidth=4;
        Path path = new Path();
      //  path.addCircle(myWidth,myHeight,radius,Path.Direction.CW);
        paint.setStrokeWidth(strokeWidth);
        canvas.drawCircle(myWidth,myHeight,radius,paint);
       // canvas.drawTextOnPath("N ,S ,W, E",path,0f,0f,paint);
    }
}

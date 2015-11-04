package com.example.jariwher.sunshine.app;

/**
 * Created by jariwher on 10/28/2015.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

public class Compass extends View {

    private float direction;

    public Compass(Context context) {
        super(context);

    }

    public Compass(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public Compass(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(
                MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec));
    }


    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        event.getText().add(direction + "");
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int w = getMeasuredWidth();
        int h = getMeasuredHeight();
        int r;
        if(w > h){
            r = h/2;
        }else{
            r = w/2;
        }

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.GRAY);

        canvas.drawCircle(w / 2, h / 2, r, paint);

        paint.setColor(getResources().getColor(R.color.sunshine_dark_blue));
        /*canvas.drawLine(
                w / 2,
                h / 2,
                (float) (w / 2 + r * Math.sin(-direction)),
                (float) (h / 2 - r * Math.cos(-direction)),
                paint);*/
        float originX = (float) w / 2;
        float originY = (float) h / 2;
        // x = length * cos (angle in radians);
        // y = length * sin (angle in radians)
        // convert degree = pi/180 radians.
        float convertToRadians = (float)(Math.PI / 180);
        float needleY = (float) (Math.cos(direction * convertToRadians) * r);
        float needleX = (float) (Math.sin(direction * convertToRadians) * r);

        // Draw needle (line: int x1, int y1, int x2, int y2)
        // angle 0 is at bottom of circle, we want it at the top, so
        // use - to flip the y for N to point up
        // the X for East/West is OK, so leave it positive
        canvas.drawLine(originX, originY,
                originX + needleX, originY - needleY, paint);
        float rightarrowX = needleX + 4;
        float rightarrowY = needleY - 4;


    }

    public void update(float dir){
        direction = dir;

        // Call invalidate to force drawing on page.

        invalidate();
    }


}

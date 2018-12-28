package com.sauce.mytablayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {
    private Paint paint;
    private Path path;

    public MyView(Context context){
        super(context);
        paint = new Paint();
        path = new Path();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            path.moveTo(x, y);
        }
        else if(event.getAction() == MotionEvent.ACTION_MOVE){
            path.lineTo(x, y);
        }
        else if(event.getAction() == MotionEvent.ACTION_UP){ }

        invalidate();
        return true;
    }
}

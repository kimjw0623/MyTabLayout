package com.sauce.mytablayout;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MyView extends View {
    private List<Path> lines = new ArrayList<Path>();
    private List<String> colors = new ArrayList<String>();
    private Path path;
    private Paint paint;

    public MyView(Context context){
        super(context);
        paint = new Paint();
        path = new Path();
        lines.add(path);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(Path p : lines){
            canvas.drawPath(p, paint);
        }
    }

    public void changeColor(String color){
        paint = new Paint();
        paint.setColor(Color.parseColor(color));
        paint.setStyle(Paint.Style.STROKE);
        Path tp = new Path();
        path.addPath(tp);
        lines.add(path);

        if(color.equals("#ffffff")){
            paint.setStrokeWidth(20f);
        }
        else paint.setStrokeWidth(10f);

    }

    public void cleanPath(){
        lines.clear();
        path = new Path();
        lines.add(path);
        invalidate();
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

/*class Point {
    private float x, y, r;
    private Paint paint;
    Point(float x, float y){
        this.x = x;
        this.y = y;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public void setPaint(Paint paint){
        this.paint = paint;
    }
    public Paint getPaint(){
        return paint;
    }
    public void setR(float r){
        this.r = r;
    }
    public float getR(){
        return r;
    }
}*/
package com.sauce.mytablayout;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MyView extends View {
    private List<Path> lines = new ArrayList<Path>();
    private List<Paint> colors = new ArrayList<Paint>();
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
        colors.add(paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i=0; i<lines.size(); i++){
            canvas.drawPath(lines.get(i), colors.get(i));
        }
    }

    public void changeColor(String color){
        paint.setColor(Color.parseColor(color));
        if(color.equals("#ffffff")){
            paint.setStrokeWidth(130f);
        }
        else paint.setStrokeWidth(10f);

    }

    public void cleanPath(){
        lines.clear();
        colors.clear();
        path = new Path();
        lines.add(path);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
        colors.add(paint);
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
        else if(event.getAction() == MotionEvent.ACTION_UP){
            path = new Path();
            lines.add(path);
            int color = paint.getColor();
            paint = new Paint();
            paint.setColor(color);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10f);
            colors.add(paint);
        }


        invalidate();
        return true;
    }

    //public void saveDrawing(LinearLayout ll){ }
}
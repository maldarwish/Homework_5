package com.example.homework_5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;


public class MyCanvas extends View {
    HashMap <Integer, Path> activePaths;
    Paint pathPaint;
    ArrayList<Path> paths;
    ArrayList<Paint> paints;
    Canvas canvas2;

    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        activePaths = new HashMap<>();
        pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathPaint.setColor(Color.RED);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(70);
        paths = new ArrayList<>();
        paints = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        for(Path path: activePaths.values()) {
//            canvas.drawPath(path, pathPaint);
//        }

        for(int i =0; i < paths.size(); i++) {
            pathPaint.setColor(paints.get(i).getColor());
            canvas.drawPath(paths.get(i), pathPaint);
        }

        super.onDraw(canvas);
    }

    public void addPath(int id, float x, float y) {
        Path path = new Path();
        path.moveTo(x, y);
        activePaths.put(id, path);
        paths.add(path);
        paints.add(pathPaint);
        invalidate();
    }

    public void updatePath(int id, float x, float y) {
        Path path = activePaths.get(id);
        if(path != null){
            path.lineTo(x, y);
        }
        invalidate();
    }

    public void removePath(int id) {
        if(activePaths.containsKey(id)){
            activePaths.remove(id);
        }
        invalidate();
    }

    public void undo() {
        if(!paths.isEmpty()) {
            paths.remove(paths.size() - 1);
            activePaths.remove(paths.size() - 1);
        }
        invalidate();
    }

    public void clear() {
        if(!paths.isEmpty()) {
            paths.clear();
            activePaths.clear();
        }
        invalidate();
    }

    public void redColor() {
        pathPaint.setColor(Color.RED);
    }

    public void blueColor() {
        pathPaint.setColor(Color.BLUE);
    }

    public void greenColor() {
        pathPaint.setColor(Color.GREEN);
    }

    public void doubleTap(float l, float t) {
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        canvas2.drawBitmap(logo, l, t, null);
    }

}

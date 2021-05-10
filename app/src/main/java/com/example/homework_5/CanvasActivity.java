package com.example.homework_5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


public class CanvasActivity extends AppCompatActivity {

    TouchListener touchListener;
    ImageView imageView;
    MyCanvas myCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        touchListener = new TouchListener(this);
        myCanvas = (MyCanvas) findViewById(R.id.myCanvas);
        myCanvas.setOnTouchListener(touchListener);

        imageView = (ImageView) findViewById(R.id.imageView);

        Bitmap bitmap = (Bitmap) this.getIntent().getParcelableExtra("image");
        imageView.setImageBitmap(bitmap);
    }

    public void addPath(int id, float x, float y) {
        myCanvas.addPath(id, x, y);
    }

    public void updatePath(int id, float x, float y) {
        myCanvas.updatePath(id, x, y);
    }

    public void removePath(int id) { myCanvas.removePath(id); }

    public void red(View view) { myCanvas.redColor(); }

    public void blue(View view) { myCanvas.blueColor(); }

    public void green(View view) { myCanvas.greenColor(); }

    public void undoPath(View view) { myCanvas.undo(); }

    public void clearPaths(View view) { myCanvas.clear(); }

    public void onDoubleTap(float left, float top) {
        myCanvas.doubleTap(left, top);
    }

//    public void onLongPress() {
//        Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if(takePicIntent.resolveActivity(getPackageManager()) != null){
//            startActivityForResult(takePicIntent, REQUEST_IMAGE);
//        }
//    }

    public void done(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
package edu.ucsb.cs.cs184.holl.thollerermmmedley;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.core.view.MotionEventCompat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MultitouchCanvasView extends SurfaceView implements SurfaceHolder.Callback {

    public MultitouchCanvasView(Context context, AttributeSet attributes) {
        super(context, attributes);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //Called very first after constructor
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //Called when orientation changes
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Handle MotionEvent types here
        return true;
    }
}
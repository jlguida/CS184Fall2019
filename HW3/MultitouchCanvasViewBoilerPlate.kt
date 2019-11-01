package com.apps.guida.cs184_f19_hw3_solution.ui.multitouch

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.util.*
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlin.collections.HashMap
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.util.TypedValue
import androidx.core.view.MotionEventCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.apps.guida.cs184_f19_hw3_solution.R

class MultitouchCanvasView(context: Context, attr: AttributeSet): SurfaceView(context, attr) , SurfaceHolder.Callback {

    init {
        //Initialize member variables
        Log.d("MULTITOUCH", "CanvasView: Constructor")
        holder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        //Called very first after constructor
        Log.d("MULTITOUCH", "CanvasView: surfaceCreated")
    }
    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        Log.d("MULTITOUCH", "CanvasView: surfaceChanged")
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        //Called when screen rotates
        Log.d("MULTITOUCH", "CanvasView: surfaceDestroyed")
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //Handle the different types of MotionEvents here
        Log.d("MULTITOUCH", "CanvasView: onTouchEvent")
        return true
    }
}


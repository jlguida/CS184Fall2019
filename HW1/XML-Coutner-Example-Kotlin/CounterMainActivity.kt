package com.apps.guida.examplecounterkotlin1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    var tv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public fun incrementCounter(view: View) {
        tv = findViewById(R.id.textView)
        tv?.setText((tv?.text.toString().toInt() + 1).toString())
    }
}

package com.apps.guida.examplecounterkotlin2

import android.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //declare params for the layouts items
    var params: LinearLayout.LayoutParams? = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )

    //declare params for the layout
    var layout_params: ActionBar.LayoutParams? = ActionBar.LayoutParams(
        ActionBar.LayoutParams.MATCH_PARENT,
        ActionBar.LayoutParams.MATCH_PARENT
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)


        //declare UI elements
        var linearLayout = LinearLayout(this)
        var textView = TextView(this)
        var button = Button(this)

        //Set UI elements using synthetic properties
        textView.text = "0"
        textView.layoutParams = params
        button.layoutParams = params
        button.text = "Click Me!"
        linearLayout.layoutParams = layout_params

        //set onClick listener
        button.setOnClickListener{
           textView.text = (textView.text.toString().toInt() + 1).toString()
    }

        //add UI elements to layout
        linearLayout.addView(textView)
        linearLayout.addView(button)

        //add layout into the content view
        this.addContentView(linearLayout, layout_params)
    }
}

package com.apps.guida.examplecountertest2;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //Create a linear layout
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        //Create text view for counter
        final TextView textView = new TextView(this);
        textView.setText("0");
        textView.setLayoutParams(params);

        //Create a button for counting
        Button button = new Button(this);
        button.setText("Click Me!");
        button.setLayoutParams(params);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                textView.setText(String.valueOf(Integer.valueOf(textView.getText().toString()) + 1));
            }
        });

        //Add to linear layout
        linearLayout.addView(textView);
        linearLayout.addView(button);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT
        );
        this.addContentView(linearLayout, layoutParams);
    }
}

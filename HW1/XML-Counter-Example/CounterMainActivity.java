package com.apps.guida.examplecountertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //argument 'view' is passed from view that is clicked
    public void incrementCounter(View view) {
        TextView tv = findViewById(R.id.CounterCount);
        int count = Integer.parseInt(tv.getText().toString()) + 1;
        tv.setText(String.valueOf(count));
    }
}

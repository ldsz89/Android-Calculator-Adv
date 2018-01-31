package com.example.dre.relativecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {
    private TextView _screen;
    private String display = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        _screen = (TextView)findViewById(R.id.textView);
        _screen.setText(display);
    }

    protected void updateScreen() {
        _screen.setText(display);
    }

    public void onClickNumber (View v) {
        Button btn = (Button) v;
        display += btn.getText();
        updateScreen();
    }

    public void onClickClear (View v) {
        Button btn = (Button) v;
        display = "";
        updateScreen();
    }

    public void onClickOp (View v) {
        Button btn = (Button) v;
        display += btn.getText();
        updateScreen();
    }
}

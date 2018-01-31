package com.example.dre.relativecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {
    private TextView _screen;
    private String display = "";
    private Float operand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        _screen = (TextView)findViewById(R.id.textView);
        display = "";
        _screen.setText(display);
        operand = null;
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
        operand = null;
    }

    public void onClickOp (View v) {
        // No number has been entered
        if(display.isEmpty()) {
            return;
        }
        // Display is not empty and operand has not been set, set operand
        if(!operand) {
            operand = Float(display.toString());
        }
        else if(operand) {
            // Operand has been set, perform operation
        }
        Button btn = (Button) v;
        display += btn.getText();
        updateScreen();
    }

    public void onClickEquals (View v) {

    }
}

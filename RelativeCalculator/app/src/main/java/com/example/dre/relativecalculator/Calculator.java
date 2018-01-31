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
    private Float result = 1.0f;
    private int loopCount = 0;

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
        int toggleMult = -1;
        int toggleDiv = -1;
        int toggleAdd = -1;
        int toggleSub = -1;

        // No number has been entered
        if(display.isEmpty()) {
            return;
        }
        // Display is not empty and operand has not been set, set operand
        if(operand == null) {
            operand = Float.parseFloat(display.toString());
        }
        else if(operand != null) {
            // Operand has been set, perform operation
            //Parse through the display string to ensure that two numbers and an operand exist in the correct order.
            //Check multiplication first.
            System.out.println("\n\n\n\nGot here!!!");
            String[] tokens = display.split("X|\\+");
            for (String token : tokens) {
                System.out.println(token);
                result *= operand;
                loopCount++;
            }
            if(loopCount % 2 == 0) {
                System.out.println("\nGot in the if\n");
                //This means two numbers have been entered, calculate the result and display it.
                display = "";
                display += result;
                updateScreen();
            }

        }


            System.out.println("\nGot to else!\n");
            Button btn = (Button) v;
            display += btn.getText();
            updateScreen();

    }

    public void onClickEquals (View v) {

    }
}

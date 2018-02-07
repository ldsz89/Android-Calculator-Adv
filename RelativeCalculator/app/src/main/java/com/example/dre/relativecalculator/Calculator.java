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
    private Float addresult = 0.0f;
    private int loopCount = 0;
    private int addLoopCount = 0;
    private String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        _screen = (TextView)findViewById(R.id.textView);
        display = "";
        _screen.setText(display);
        operand = null;
        operation = "";
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
        loopCount = 0;
        addLoopCount = 0;
        operation = "";
    }

    public void onClickOp (View v) {
        int toggleMult = -1;
        int toggleDiv = -1;
        int toggleAdd = -1;
        int toggleSub = -1;

        System.out.println("\nThe ID is: " + v.getTag() + "\n");

        // No number has been entered
        if (display.isEmpty()) {
            return;
        }
        // Display is not empty and operand has not been set, set operand
        if (operand == null) {
            operand = Float.parseFloat(display.toString());
            switch (v.getTag().toString()) {
                case "add":
                    operation = "add";
                    display += "+";
                    break;
                case "sub":
                    operation = "sub";
                    display += "-";
                    break;
                case "mult":
                    operation = "mult";
                    display += "X";
                    break;
                case "div":
                    operation = "div";
                    display += "/";
                    break;
            }
        } else if (operand != null) {
            // Operand has been set, perform operation
            //Parse through the display string to ensure that two numbers and an operand exist in the correct order.
            //Check multiplication first.
            evaluate();
            Button btn = (Button) v;
            switch (v.getTag().toString()) {
                case "add":
                    operation = "add";
                    display += "+";
                    break;
                case "sub":
                    operation = "sub";
                    display += "-";
                    break;
                case "mult":
                    operation = "mult";
                    display += "X";
                    break;
                case "div":
                    operation = "div";
                    display += "/";
                    break;
            }
        }
        updateScreen();
    }

    public void onClickEquals (View v) {
        System.out.println("\nGot to Equals fxn\n");
        if(operation.isEmpty()) {
            return;
        }
        evaluate();
        updateScreen();
    }

    private void evaluate () {
        String[] tokens = display.split("X|\\+|\\-|\\/|\\%");

        switch (operation.toString()) {
            case "add":
                result = Float.parseFloat(tokens[0]) + Float.parseFloat(tokens[1]);
                break;
            case "sub":
                result = Float.parseFloat(tokens[0]) - Float.parseFloat(tokens[1]);
                break;
            case "mult":
                result = Float.parseFloat(tokens[0]) * Float.parseFloat(tokens[1]);
                break;
            case "div":
                result = Float.parseFloat(tokens[0]) / Float.parseFloat(tokens[1]);
                break;
            case "mod":
                result = Float.parseFloat(tokens[0]) % Float.parseFloat(tokens[1]);
                break;
        }
        System.out.println("\nResult: " + result.toString() + "\n");
        operand = result;
        display = result.toString();
        operation = "";
        return;
    }
}

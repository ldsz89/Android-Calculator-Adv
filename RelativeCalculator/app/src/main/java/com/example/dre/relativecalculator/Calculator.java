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
        if(display.isEmpty()) {
            return;
        }
        // Display is not empty and operand has not been set, set operand
        if(operand == null) {
            operand = Float.parseFloat(display.toString());
            switch (v.getTag().toString()) {
                case "add":
                    operation = "add";
                    break;
                case "sub":
                    operation = "sub";
                    break;
                case "mult":
                    operation = "mult";
                    break;
                case "div":
                    operation = "div";
                    break;
            }
        }
        else if(operand != null) {
            // Operand has been set, perform operation
            //Parse through the display string to ensure that two numbers and an operand exist in the correct order.
            //Check multiplication first.
            System.out.println("\n\n\n\nGot here!!!");
            String[] tokens = display.split("X|\\+|\\-|\\/");

            //Have a switch statement here to handle whether it is multiplication, addition, subtraction, modulus, or division.
            if(v.getTag().toString().equals("mult")) {
                System.out.println("\nmult was entered\n");
//                for (String token : tokens) {
//                    System.out.println(token);
//                    result *= Float.parseFloat(token);
//                    loopCount++;
//                }
                result = Float.parseFloat(tokens[0]) * Float.parseFloat(tokens[1]);
                loopCount = 2;
            }
            else if(v.getTag().toString().equals("add")) {
                System.out.println("\nadd was entered\n");
//                for (String token : tokens) {
//                    System.out.println(token);
//                    addresult += Float.parseFloat(token);
//                    System.out.println("\nThe addition result is: " + addresult + "\n");
//                    addLoopCount++;
//                }
                result = Float.parseFloat(tokens[0]) * Float.parseFloat(tokens[1]);
                addLoopCount = 2;
            }
            else if(v.getTag().toString().equals("sub")) {
                System.out.println("\nsub was entered\n");
                for (String token : tokens) {
                    System.out.println(token);
                    if(loopCount == 0) {
                        result = Float.parseFloat(token);
                    }
                    else {
                        result -= Float.parseFloat(token);
                    }
                    loopCount++;
                }
            }
            else if(v.getTag().toString().equals("div")) {
                System.out.println("\nmod was entered\n");
                for (String token : tokens) {
                    System.out.println(token);
                    if(loopCount == 0) {
                        result = Float.parseFloat(token);
                    }
                    else {
                        if(Float.parseFloat(token) == 0) {
                            System.out.println("\nGot to divide by 0 error.");
                            System.out.println("\n" + Float.parseFloat(token) + "\n");
                            display = "CANNOT DIVIDE BY 0!!!";
                            //Fix the issue in which the appended operation stll appears. Maybe use a GOTO just this once to break to the end of the function? Using return; doesn't work, and break; still has it stay there.
                            break;
                        }
                        result /= Float.parseFloat(token);
                    }
                    loopCount++;
                }
            }
            else if(v.getTag().toString().equals("mod")) {
                System.out.println("\nmod was entered\n");
                for (String token : tokens) {
                    System.out.println(token);
                    result *= operand;
                    loopCount++;
                }
            }

            if(loopCount % 2 == 0 && loopCount != 0) {
                System.out.println("\nGot in the if\n");
                //This means two numbers have been entered, calculate the result and display it.
                display = "";
                display += result;
                updateScreen();
                //Reset loopCount to 0, otherwise there can be errors when you try to add after multiplying, etc.
                loopCount = 0;
            }
            //Need a different one here because we need to add result or addresult depending.
            else if(addLoopCount % 2 == 0 && addLoopCount != 0) {
                System.out.println("\nGot in the if\n");
                //This means two numbers have been entered, calculate the result and display it.
                display = "";
                display += addresult;
                updateScreen();
                //Reset addLoopCount to 0 here as well.
                addLoopCount = 0;
            }
        }


            System.out.println("\nGot to else!\n");
            Button btn = (Button) v;
            display += btn.getText();
            updateScreen();

    }

    public void onClickEquals (View v) {
        System.out.println("\nGot to Equals fxn\n");
        // Operation has not been set, calculation cannot be performed
        if(operation.isEmpty()) {
            return;
        }

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
        updateScreen();
    }
}

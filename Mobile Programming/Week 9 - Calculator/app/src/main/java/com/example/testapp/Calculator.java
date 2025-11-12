package com.example.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class Calculator extends Activity {
    EditText editText1, editText2;
    TextView textResult;
    Button buttonAdd, buttonSub, buttonMul, buttonDiv;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        editText1 = (EditText) findViewById(R.id.edit_text_1);
        editText2 = (EditText) findViewById(R.id.edit_text_2);
        textResult = (TextView) findViewById(R.id.text_view_1);

        buttonAdd = (Button) findViewById(R.id.button_add);
        buttonSub = (Button) findViewById(R.id.button_subtract);
        buttonMul = (Button) findViewById(R.id.button_multiply);
        buttonDiv = (Button) findViewById(R.id.button_divide);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                String number1 = editText1.getText().toString();
                String number2 = editText2.getText().toString();
                double result = Double.parseDouble(number1) + Double.parseDouble(number2);
                textResult.setText("Result: " + result);
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                String number1 = editText1.getText().toString();
                String number2 = editText2.getText().toString();
                double result = Double.parseDouble(number1) - Double.parseDouble(number2);
                textResult.setText("Result: " + result);
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                String number1 = editText1.getText().toString();
                String number2 = editText2.getText().toString();
                double result = Double.parseDouble(number1) * Double.parseDouble(number2);
                textResult.setText("Result: " + result);
            }
        });

        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                String number1 = editText1.getText().toString();
                String number2 = editText2.getText().toString();
                double result = Double.parseDouble(number1) / Double.parseDouble(number2);
                textResult.setText("Result: " + result);
            }
        });
    }
}
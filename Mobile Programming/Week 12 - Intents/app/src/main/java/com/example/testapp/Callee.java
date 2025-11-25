package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Callee extends AppCompatActivity {

    Button buttonAdd, buttonSub, buttonDiv,  buttonMul;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.callee);

        buttonAdd = (Button) findViewById(R.id.button_add);
        buttonSub = (Button) findViewById(R.id.button_sub);
        buttonDiv = (Button) findViewById(R.id.button_div);
        buttonMul = (Button) findViewById(R.id.button_mul);
        Intent inIntent = getIntent();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outIntent = new Intent(getApplicationContext(), Caller.class);
                double sum = inIntent.getDoubleExtra("num1", 0) + inIntent.getDoubleExtra("num2", 0);
                outIntent.putExtra("RESULT_SUM", sum);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outIntent = new Intent(getApplicationContext(), Caller.class);
                double result = inIntent.getDoubleExtra("num1", 0) - inIntent.getDoubleExtra("num2", 0);
                outIntent.putExtra("RESULT_SUB", result);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });

        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outIntent = new Intent(getApplicationContext(), Caller.class);
                double result = inIntent.getDoubleExtra("num1", 0) / inIntent.getDoubleExtra("num2", 0);
                outIntent.putExtra("RESULT_DIV", result);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outIntent = new Intent(getApplicationContext(), Caller.class);
                double result = inIntent.getDoubleExtra("num1", 0) * inIntent.getDoubleExtra("num2", 0);
                outIntent.putExtra("RESULT_MUL", result);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }
}
package com.example.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Caller extends Activity {
    EditText editText1, editText2;
    Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caller);
        editText1 = findViewById(R.id.edit_text_1);
        editText2 = findViewById(R.id.edit_text_2);
        buttonSend = findViewById(R.id.button_send);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Callee.class);
                intent.putExtra("num1", Double.parseDouble(editText1.getText().toString()));
                intent.putExtra("num2", Double.parseDouble(editText2.getText().toString()));
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras == null) return;

            if (extras.containsKey("RESULT_SUM")) {
                double v = extras.getDouble("RESULT_SUM");
                Toast.makeText(this, "Sum: " + v, Toast.LENGTH_SHORT).show();
            } else if (extras.containsKey("RESULT_SUB")) {
                double v = extras.getDouble("RESULT_SUB");
                Toast.makeText(this, "Subtract: " + v, Toast.LENGTH_SHORT).show();
            } else if (extras.containsKey("RESULT_DIV")) {
                double v = extras.getDouble("RESULT_DIV");
                Toast.makeText(this, "Divide: " + v, Toast.LENGTH_SHORT).show();
            } else if (extras.containsKey("RESULT_MUL")) {
                double v = extras.getDouble("RESULT_MUL");
                Toast.makeText(this, "Multiply: " + v, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
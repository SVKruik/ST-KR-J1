package com.example.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class MainActivity extends Activity {
    CheckBox checkbox1;
    TextView textView2;
    RadioGroup radioGroup1;
    Button button1;
    ImageView imageView1;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkbox1 = (CheckBox) findViewById(R.id.check_box_1);
        textView2 = (TextView) findViewById(R.id.text_view_2);
        radioGroup1 = (RadioGroup) findViewById(R.id.radio_group_1);
        button1 = (Button) findViewById(R.id.button_1);
        imageView1 = (ImageView) findViewById(R.id.image_view_1);

        checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b) {
                if (checkbox1.isChecked()) {
                    textView2.setVisibility(TextView.VISIBLE);
                    radioGroup1.setVisibility(RadioGroup.VISIBLE);
                    button1.setVisibility(Button.VISIBLE);
                    imageView1.setVisibility(ImageView.VISIBLE);
                } else {
                    textView2.setVisibility(TextView.INVISIBLE);
                    radioGroup1.setVisibility(RadioGroup.INVISIBLE);
                    button1.setVisibility(Button.INVISIBLE);
                    imageView1.setVisibility(ImageView.INVISIBLE);
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int checkedButton = radioGroup1.getCheckedRadioButtonId();

                if (checkedButton == R.id.radio_button_1) {
                    Toast.makeText(MainActivity.this, "Image 1 is selected.", Toast.LENGTH_SHORT).show();

                    imageView1.setImageResource(R.drawable.image1);
                } else if (checkedButton == R.id.radio_button_2) {
                    Toast.makeText(MainActivity.this, "Image 2 is selected.", Toast.LENGTH_SHORT).show();

                    imageView1.setImageResource(R.drawable.image2);
                } else if (checkedButton == R.id.radio_button_3) {
                    Toast.makeText(MainActivity.this, "Image 3 is selected.", Toast.LENGTH_SHORT).show();

                    imageView1.setImageResource(R.drawable.image3);
                } else if (checkedButton == R.id.radio_button_4) {
                    Toast.makeText(MainActivity.this, "Image 4 is selected.", Toast.LENGTH_SHORT).show();

                    imageView1.setImageResource(R.drawable.image4);
                } else if (checkedButton == R.id.radio_button_5) {
                    Toast.makeText(MainActivity.this, "Image 5 is selected.", Toast.LENGTH_SHORT).show();

                    imageView1.setImageResource(R.drawable.image5);
                } else {
                    Toast.makeText(MainActivity.this, "Error: No images selected.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
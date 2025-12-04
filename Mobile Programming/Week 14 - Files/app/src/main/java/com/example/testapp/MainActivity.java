package com.example.testapp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {
    Button buttonRead, buttonWrite;
    EditText editText;
    final String FILE_NAME = "Example.txt";
    String contents;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        buttonWrite = findViewById(R.id.button_write);
        buttonRead = findViewById(R.id.button_read);
        editText = findViewById(R.id.edit_text_1);
        textView = findViewById(R.id.text_view_1);

        buttonWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(getFilesDir(), FILE_NAME);

                try {
                    FileOutputStream fileStream = new FileOutputStream(file);
                    OutputStreamWriter outputStream = new OutputStreamWriter(fileStream);
                    BufferedWriter buffer = new BufferedWriter(outputStream);

                    contents = editText.getText().toString();
                    buffer.write(contents);

                    buffer.close();
                    outputStream.close();
                    fileStream.close();
                } catch (FileNotFoundException exception) {
                    Toast.makeText(getApplicationContext(), "Could not find the target file.", Toast.LENGTH_SHORT).show();
                } catch (Exception exception) {
                    exception.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Something went wrong while trying to write.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(getFilesDir(), FILE_NAME);

                try {
                    FileInputStream fileStream = new FileInputStream(file);
                    InputStreamReader inputStream = new InputStreamReader(fileStream);
                    BufferedReader buffer = new BufferedReader(inputStream);

                    textView.setText("");
                    while ((contents = buffer.readLine()) != null) {
                        textView.setText(textView.getText() + (contents + "\n"));
                    }

                    buffer.close();
                    inputStream.close();
                    fileStream.close();
                } catch (FileNotFoundException exception) {
                    Toast.makeText(getApplicationContext(), "Could not find the target file.", Toast.LENGTH_SHORT).show();
                } catch (Exception exception) {
                    exception.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Something went wrong while trying to write.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
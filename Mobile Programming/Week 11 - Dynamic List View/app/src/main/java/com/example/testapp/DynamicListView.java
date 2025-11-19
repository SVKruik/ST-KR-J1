package com.example.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DynamicListView extends Activity {
    EditText editText1;
    Button buttonAdd;
    ListView listView1;
    ArrayAdapter<String> adapter;
    ArrayList<String> array;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_list_view);

        editText1 = findViewById(R.id.editText1);
        buttonAdd = findViewById(R.id.buttonAdd);
        listView1 = findViewById(R.id.listView1);

        array = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
        listView1.setAdapter(adapter);

         // Add
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                String text = editText1.getText().toString();
                Toast.makeText(getApplicationContext(), "Added " + text, Toast.LENGTH_SHORT).show();
                array.add(text);
                adapter.notifyDataSetChanged();
            }
        });

        // Select
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Clicked on " + array.get(i), Toast.LENGTH_SHORT).show();
            }
        });

        // Delete
        listView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Removed " + array.get(i), Toast.LENGTH_SHORT).show();
                array.remove(i);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
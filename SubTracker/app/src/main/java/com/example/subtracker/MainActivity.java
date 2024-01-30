package com.example.subtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fb;
    ArrayAdapter<databaseModel> subArrayAdapter;
    DataBaseHelper dataBaseHelper;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.subListView);

        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        subArrayAdapter = new ArrayAdapter<databaseModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        listView.setAdapter(subArrayAdapter);

        fb = findViewById(R.id.fab);
        Intent intent = new Intent(this, AddActivity.class);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
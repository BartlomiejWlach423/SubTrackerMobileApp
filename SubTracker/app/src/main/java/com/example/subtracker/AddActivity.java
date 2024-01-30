package com.example.subtracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;

public class AddActivity extends Activity {

    Button buttonAdd;
    ImageButton buttonBack;
    EditText nameEditText, costEditText, paymentEditText;
    ArrayAdapter<databaseModel> subArrayAdapter;
    DataBaseHelper dataBaseHelper;

    ListView listView;
    Intent intent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity);

        buttonAdd = findViewById(R.id.Button);
        buttonBack = findViewById(R.id.backButton);
        nameEditText = findViewById(R.id.subNameEditText);
        costEditText = findViewById(R.id.subCostEditText);
        paymentEditText = findViewById(R.id.paymentDayEditText);
        listView = findViewById(R.id.listView);

        intent = new Intent(this, MainActivity.class);

        dataBaseHelper = new DataBaseHelper(AddActivity.this);

        subArrayAdapter = new ArrayAdapter<databaseModel>(AddActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        listView.setAdapter(subArrayAdapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseModel subModel;
                try {
                    subModel = new databaseModel(nameEditText.getText().toString(),Float.parseFloat(costEditText.getText().toString()), Integer.parseInt(paymentEditText.getText().toString()),-1);
                    Toast.makeText(AddActivity.this, subModel.toString(), Toast.LENGTH_SHORT).show();
                }catch (Exception e) {
                    Toast.makeText(AddActivity.this, "Error creating customer", Toast.LENGTH_SHORT).show();
                    subModel = new databaseModel("error",0.0f,0, -1);
                }

                boolean success = dataBaseHelper.addOne(subModel);
                Toast.makeText(AddActivity.this, "success= "+ success, Toast.LENGTH_SHORT).show();

                subArrayAdapter = new ArrayAdapter<databaseModel>(AddActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
                listView.setAdapter(subArrayAdapter);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }


}

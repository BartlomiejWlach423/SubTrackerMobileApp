package com.example.subtracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class AddActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity);

        Button buttonAdd = findViewById(R.id.Button);
        ImageButton buttonBack = findViewById(R.id.backButton);

        EditText nameEditText, costEditText, paymentEditText;
        nameEditText = findViewById(R.id.subNameEditText);
        costEditText = findViewById(R.id.subCostEditText);
        paymentEditText = findViewById(R.id.paymentDayEditText);

        Intent intent = new Intent(this, MainActivity.class);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(AddActivity.this);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseModel subModel;
                try {
                    subModel = new databaseModel(nameEditText.getText().toString(),Float.parseFloat(costEditText.getText().toString()), Integer.parseInt(paymentEditText.getText().toString()),-1);
                }catch (Exception e) {
                    subModel = new databaseModel("error",0.0f,0, -1);
                }

                boolean success = dataBaseHelper.addOne(subModel);
                startActivity(intent);
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

package com.example.subtracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class DetailActivity extends Activity {
    ImageButton buttonBack;
    Button buttonDelete;
    EditText nameEditText, costEditText, paymentDayEditText;
    DataBaseHelper dbHelper;
    int reciveID = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        nameEditText = findViewById(R.id.detailNameEditText);
        costEditText = findViewById(R.id.detailCostEditText);
        paymentDayEditText = findViewById(R.id.detailPaymentDayEditText);
        buttonDelete = findViewById(R.id.deleteButton);

        Intent intent = getIntent();
        if (intent != null){
            reciveID = intent.getIntExtra("ITEM_ID", -1);
            System.out.println("reciveID = " + reciveID);
        }

        dbHelper = new DataBaseHelper(DetailActivity.this);
        nameEditText.setText(dbHelper.getNameById(reciveID));
        costEditText.setText(String.valueOf(dbHelper.getCostById(reciveID)));
        paymentDayEditText.setText(String.valueOf(dbHelper.getPaymentDayById(reciveID)));


    }

    public void detailBackButtonOnClick(View view) {
        Intent backToMainIntent = new Intent(this, MainActivity.class);
        startActivity(backToMainIntent);
    }

    public void deleteButtonOnClick(View view) {
        dbHelper.deleteOne(reciveID);
        detailBackButtonOnClick(view);
    }
}

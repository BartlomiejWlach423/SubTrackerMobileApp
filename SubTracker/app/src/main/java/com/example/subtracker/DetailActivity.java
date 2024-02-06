package com.example.subtracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class DetailActivity extends Activity {

    EditText nameEditText, costEditText, paymentDayEditText;
    int reciveID = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        nameEditText = findViewById(R.id.detailNameEditText);
        costEditText = findViewById(R.id.detailCostEditText);
        paymentDayEditText = findViewById(R.id.detailPaymentDayEditText);

        Intent intent = getIntent();
        if (intent != null)
            reciveID = intent.getIntExtra("ITEM_ID", -1);

        DataBaseHelper dbHelper = new DataBaseHelper(DetailActivity.this);

        nameEditText.setText(dbHelper.getNameById(reciveID));
        costEditText.setText(String.valueOf(dbHelper.getCostById(reciveID)));
        paymentDayEditText.setText(String.valueOf(dbHelper.getPaymentDayById(reciveID)));
    }

    public void deleteButtonOnClick(View view) {
        DataBaseHelper dbHelper = new DataBaseHelper(DetailActivity.this);
        dbHelper.deleteOne(reciveID);
        finish();
    }


    public void saveButtonOnClick(View view) {
        DataBaseHelper dbHelper = new DataBaseHelper(DetailActivity.this);
        dbHelper.updateData(reciveID, nameEditText.getText().toString(), Float.parseFloat(costEditText.getText().toString()), Integer.parseInt(paymentDayEditText.getText().toString()));
        finish();
    }

}

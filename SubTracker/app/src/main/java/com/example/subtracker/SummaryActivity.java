package com.example.subtracker;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
public class SummaryActivity extends AppCompatActivity {
    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_activity);

        setSummaryListView();
        setReport();
    }

    void setReport() {
        List<Float> costList = dataBaseHelper.getEveryCost();
        float monthlyCost = 0, yearCost = 0;
        for (short i = 0; i < costList.size(); i++){
            monthlyCost+=costList.get(i);
            yearCost+=costList.get(i);
        }
        yearCost*=12;

        TextView summaryCostTextView = findViewById(R.id.summaryCost);
        summaryCostTextView.setText(getString(R.string.summary_month)+" "+monthlyCost+" "+getString(R.string.currency)+getString(R.string.summary_year)+" "+yearCost+" "+getString(R.string.currency));
    }

    void setSummaryListView(){
        ArrayList<SummaryListData> listArrayData = new ArrayList<>();
        ListView lv = findViewById(R.id.summaryListView);
        SummaryListAdapter summaryListAdapter;
        SummaryListData summaryListData;

        List<String> nameList = dataBaseHelper.getEveryName();
        List<Float> costList = dataBaseHelper.getEveryCost();

        for (short i = 0; i < nameList.size(); i++){
            summaryListData = new SummaryListData(nameList.get(i),costList.get(i));
            listArrayData.add(summaryListData);
        }
        summaryListAdapter = new SummaryListAdapter(SummaryActivity.this, listArrayData);
        lv.setAdapter(summaryListAdapter);
    }
}

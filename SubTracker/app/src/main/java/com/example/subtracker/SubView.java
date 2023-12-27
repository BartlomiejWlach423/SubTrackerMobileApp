package com.example.subtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;


import com.example.subtracker.databinding.ActivitySubView2Binding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SubView extends AppCompatActivity {

    ActivitySubView2Binding binding;
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();
    ListData listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_view2);
        //===========================================
        binding = ActivitySubView2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] imageList = {R.drawable.hbomax,R.drawable.hbomax,R.drawable.hbomax};
        int[] dayOfMonthList = {12,25,34};
        String[] nameList = {"obj1","obj2","Hbo Max"};
        Float[] priceList = {12.5f,23.45f,29.99f};
        for(int i = 0; i < imageList.length; i++)
        {
            listData = new ListData(nameList[i],priceList[i],dayOfMonthList[i],imageList[i]);
            dataArrayList.add(listData);
        }
        listAdapter = new ListAdapter(SubView.this, dataArrayList);
        binding.listView.setAdapter(listAdapter);
        binding.listView.setClickable(true);
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SubView.this, DetailedActivity.class);
                intent.putExtra("name",nameList[i]);
                intent.putExtra("price",priceList[i]);
                intent.putExtra("day of month",dayOfMonthList[i]);
                intent.putExtra("image",imageList[i]);
                startActivity(intent);
            }
        });
        //============================================

    }



    public void OnClickFloatingButton(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
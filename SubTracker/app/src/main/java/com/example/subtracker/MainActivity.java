package com.example.subtracker;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;

import com.example.subtracker.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<ListData> listArrayData = new ArrayList<>();
    ListAdapter listAdapter;
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

        setCustomListView(dataBaseHelper);
        FloatingActionButton fb = findViewById(R.id.fab);

        Intent addIntent = new Intent(this, AddActivity.class);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(addIntent);
            }
        });

        Handler handler = new Handler();

        Runnable delayedTodayNotification, delayedTomorrowNotification, delayedFutureNotification;

        delayedTodayNotification = new Runnable() {
            @Override
            public void run() {
                checkNotification(0);
            }
        };

        delayedTomorrowNotification = new Runnable() {
            @Override
            public void run() {
                checkNotification(1);
            }
        };

        delayedFutureNotification = new Runnable() {
            @Override
            public void run() {
                checkNotification(2);
            }
        };

        handler.postDelayed(delayedTodayNotification, 4000);
        handler.postDelayed(delayedTomorrowNotification, 10000);
        handler.postDelayed(delayedFutureNotification,16000);
    }

    void checkNotification(int future){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
        List<String> nameList = dataBaseHelper.getEveryName();
        List<Float> costList = dataBaseHelper.getEveryCost();
        List<Integer> paymentList = dataBaseHelper.getEveryPaymentDay();

        Calendar calendar = Calendar.getInstance();
        int paymentDay = calendar.get(Calendar.DAY_OF_MONTH)+future;

        if (paymentDay>26) {
            int dayInMonth = listAdapter.howManyDaysIsInThisMonth();
            paymentDay-=dayInMonth;
        }

        int resource = R.string.today;

        switch (future){
            case 1:
                resource=R.string.tomorrow;
                break;
            case 2:
                resource=R.string.after_tomorrow;
                break;
            default:break;
        }

        for (short i = 0; i < paymentList.size(); i++) {
            if (paymentDay == paymentList.get(i))
                NotificationHelper.paymentDayNotify(this, nameList.get(i), costList.get(i), this.getString(resource));
        }
    }

    void setCustomListView(DataBaseHelper dataBaseHelper){
        ListData listData;
        List<String> nameList = dataBaseHelper.getEveryName();
        List<Float> costList = dataBaseHelper.getEveryCost();
        List<Integer> paymentList = dataBaseHelper.getEveryPaymentDay();
        List<Integer> idList = dataBaseHelper.getEveryId();

        for (short i = 0; i < idList.size(); i++){
            listData = new ListData(nameList.get(i),costList.get(i),paymentList.get(i),idList.get(i));
            listArrayData.add(listData);
        }
        listAdapter = new ListAdapter(MainActivity.this, listArrayData);
        binding.subListView.setAdapter(listAdapter);
        binding.subListView.setClickable(true);

        Intent detailIntent = new Intent(this, DetailActivity.class);
        binding.subListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListData clickedItem = (ListData) parent.getItemAtPosition(position);
                detailIntent.putExtra("ITEM_ID", clickedItem.getId());
                startActivity(detailIntent);
            }
        });
    }

}
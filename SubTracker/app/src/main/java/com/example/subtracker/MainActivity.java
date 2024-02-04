package com.example.subtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fb;
    ArrayAdapter<databaseModel> subArrayAdapter;
    DataBaseHelper dataBaseHelper;
    ListView listView;
    Calendar calendar = Calendar.getInstance();
    Handler handler;

    Runnable delayedTodayNotification, delayedFutureNotification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.subListView);
        fb = findViewById(R.id.fab);

        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        subArrayAdapter = new ArrayAdapter<databaseModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        listView.setAdapter(subArrayAdapter);

        Intent addIntent = new Intent(this, AddActivity.class);
        Intent detailIntent = new Intent(this, DetailActivity.class);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(addIntent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                databaseModel clickedItem = (databaseModel) parent.getItemAtPosition(position);
                detailIntent.putExtra("ITEM_ID", clickedItem.getId());
                startActivity(detailIntent);
            }
        });

        handler = new Handler();

        delayedTodayNotification = new Runnable() {
            @Override
            public void run() {
                checkToday();
            }
        };

        delayedFutureNotification = new Runnable() {
            @Override
            public void run() {
                checkFuture();
            }
        };

        handler.postDelayed(delayedTodayNotification, 2000);
        handler.postDelayed(delayedFutureNotification, 6000);
    }

    void checkToday(){
        List<String> nameList = dataBaseHelper.getEveryName();
        List<Float> costList = dataBaseHelper.getEveryCost();
        List<Integer> paymentList = dataBaseHelper.getEveryPaymentDay();

        int today = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println("Payment list size = " + paymentList.size());

        for (short i = 0; i < paymentList.size(); i++) {
            System.out.println("paymentList("+i+") = " + paymentList.get(i));
            if (today == paymentList.get(i)){
                NotificationHelper.paymentDayNotify(this, nameList.get(i), costList.get(i), this.getString(R.string.today));
                System.out.println("wysłano powiadomienie");
            }
        }
    }

    void checkFuture(){
        List<String> nameList = dataBaseHelper.getEveryName();
        List<Float> costList = dataBaseHelper.getEveryCost();
        List<Integer> paymentList = dataBaseHelper.getEveryPaymentDay();

        int futureDay = calendar.get(Calendar.DAY_OF_MONTH)+2;

        if (futureDay>26) {
            int month = calendar.get(Calendar.MONTH);
            if (month == 0 || month == 2 || month == 4 || month == 6 || month == 7 || month == 9 || month == 11) {
                futureDay = futureDay-31;
            } else if (month == 3 || month == 5 || month == 8 || month == 10) {
                futureDay = futureDay-30;
            } else {
                int year = calendar.get(Calendar.YEAR);
                if ((year%4==0 && year%100!=0) || year%400==0){
                    futureDay = futureDay-29;
                }
                else{
                    futureDay = futureDay-28;
                }
            }
        }

        System.out.println("Payment list size = " + paymentList.size());
        System.out.println("zakres dni do powiadomienia: "+(futureDay-1)+"-"+futureDay);

        for (short i = 0; i < paymentList.size(); i++) {
            System.out.println("paymentList("+i+") = " + paymentList.get(i));
            if (futureDay == paymentList.get(i)){
                NotificationHelper.paymentDayNotify(this, nameList.get(i), costList.get(i), this.getString(R.string.after_tomorrow));
            }
            else if (futureDay-1 == paymentList.get(i)){
                NotificationHelper.paymentDayNotify(this, nameList.get(i), costList.get(i), this.getString(R.string.tomorrow));
            }
            System.out.println("wysłano powiadomienie");
        }
    }
}
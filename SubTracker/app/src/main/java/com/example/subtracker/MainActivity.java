package com.example.subtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.subtracker.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<ListData> listArrayData = new ArrayList<>();
    ListAdapter listAdapter;
    ActivityMainBinding binding;
    DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
    int previousVisibleItem = 0;
    FloatingActionButton fb,summaryFB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fb = findViewById(R.id.fab);
        summaryFB = findViewById(R.id.fabSum);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent addIntent = new Intent(this, AddActivity.class);
        Intent summaryIntent = new Intent(this, SummaryActivity.class);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(addIntent);
            }
        });

        summaryFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(summaryIntent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        setCustomListView(dataBaseHelper);

        ListView listView = findViewById(R.id.subListView);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem > previousVisibleItem) {
                    fb.hide();
                    summaryFB.hide();
                } else if (firstVisibleItem < previousVisibleItem) {
                    fb.show();
                    summaryFB.show();
                }
                previousVisibleItem = firstVisibleItem;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        listAdapter.clear();
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
        listData = null;
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
                clickedItem = null;
            }
        });
    }

}


/*
    void checkNotification(){
        List<String> nameList = dataBaseHelper.getEveryName();
        List<Float> costList = dataBaseHelper.getEveryCost();
        List<Integer> paymentList = dataBaseHelper.getEveryPaymentDay();

        Handler NotificationHandler = new Handler();
        int resource;

        int paymentDay, dayInMonth;

        for (int future = 0; future<3; future++){
            Calendar calendar = Calendar.getInstance();
            paymentDay = calendar.get(Calendar.DAY_OF_MONTH)+future;

            if (paymentDay>26) {
                dayInMonth = listAdapter.howManyDaysIsInThisMonth();
                paymentDay-=dayInMonth;
            }

            switch (future){
                case 1:
                    resource =R.string.tomorrow;
                    break;
                case 2:
                    resource =R.string.after_tomorrow;
                    break;
                default:
                    resource = R.string.today;
                    break;
            }

            for (short i = 0; i < paymentList.size(); i++) {
                if (paymentDay == paymentList.get(i)) {
                    short position = i;
                    int res = resource;
                    NotificationHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            NotificationHelper.paymentDayNotify(MainActivity.this, nameList.get(position), costList.get(position), MainActivity.this.getString(res));
                            System.out.println("Wysłano: "+nameList.get(position)+"  "+MainActivity.this.getString(res));
                        }
                    }, i * 3000);
                }
            }
        }
    }

*/



        /*
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

        handler.postDelayed(delayedTodayNotification, 2000);
        handler.postDelayed(delayedTomorrowNotification, 6000);
        handler.postDelayed(delayedFutureNotification,10000);
        */




/*
    void checkNotification(int future){
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

        Handler handler = new Handler();

        for (short i = 0; i < paymentList.size(); i++) {
            if (paymentDay == paymentList.get(i))
                NotificationHelper.paymentDayNotify(this, nameList.get(i), costList.get(i), this.getString(resource));
        }
    }

*/
package com.example.subtracker;

import android.app.Application;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SubscryptionReminder extends Application {
    public void onCreate() {
        super.onCreate();
        checkNotification();
    }


    @Override
    public void onTerminate() {
        super.onTerminate();

    }

    void checkNotification(){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        ArrayList<ListData> listArrayData = new ArrayList<>();
        ListAdapter listAdapter = new ListAdapter(this, listArrayData);;
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
                            NotificationHelper.paymentDayNotify(SubscryptionReminder.this, nameList.get(position), costList.get(position), SubscryptionReminder.this.getString(res));
                            System.out.println("Wysłano: "+nameList.get(position)+"  "+SubscryptionReminder.this.getString(res));
                        }
                    }, i * 3000);
                }
            }
        }
    }

}

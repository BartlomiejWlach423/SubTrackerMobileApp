package com.example.subtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.Calendar;
import java.util.List;


public class ListAdapter extends ArrayAdapter<ListData> {
    public ListAdapter(@NonNull Context context,  List<ListData> objects) {
        super(context, R.layout.custom_list_view, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ListData listData = getItem(position);

        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_MONTH);
        int inHowManyDaysPayment = listData.payment-today;
        int daysInMonth = howManyDaysIsInThisMonth();

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_view, parent, false);
        }

        TextView nameCustomListView = convertView.findViewById(R.id.nameCustomListView);
        TextView costCustomListView = convertView.findViewById(R.id.costCustomListView);
        TextView paymentCustomListView = convertView.findViewById(Integer.parseInt(String.valueOf(R.id.paymentCustomListView)));

        nameCustomListView.setText(listData.name);
        costCustomListView.setText(String.valueOf(listData.cost)+ " " +convertView.getContext().getString(R.string.currency));

        int color = R.color.black;
        String message = "";

        if (inHowManyDaysPayment==0) {
            message ="Today";
            color = R.color.red;
        }
        else if (inHowManyDaysPayment == 1) {
            message = "Tomorrow";
            color = R.color.orange;
        }
        else if (inHowManyDaysPayment == 2) {
            message = "payment in " + inHowManyDaysPayment + " days";
            color = R.color.orange;
        }
        else if (inHowManyDaysPayment > 2){
            message = "payment in " + inHowManyDaysPayment + " days";
            color = R.color.black;
        }
        else if (inHowManyDaysPayment < 0){
            inHowManyDaysPayment += daysInMonth;
            message = "payment in " + inHowManyDaysPayment + " days";
        }
        if (listData.payment>daysInMonth) {
            color = R.color.blue;
            message = "check when!!!";
        }

        paymentCustomListView.setText(message);
        paymentCustomListView.setTextColor(ContextCompat.getColor(convertView.getContext(),color));

        return convertView;
    }

    int howManyDaysIsInThisMonth(){
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);

        if (month == 0 || month == 2 || month == 4 || month == 6 || month == 7 || month == 9 || month == 11)
            return 31;
        else if (month == 3 || month == 5 || month == 8 || month == 10)
            return 30;
        else{
            int year = calendar.get(Calendar.YEAR);
            if ((year%4==0 && year%100!=0) || year%400==0)
                return 29;
            else
                return 28;
        }
    }
}

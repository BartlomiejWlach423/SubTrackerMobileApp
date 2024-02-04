package com.example.subtracker;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_view, parent, false);
        }

        TextView nameCustomListView = convertView.findViewById(R.id.nameCustomListView);
        TextView costCustomListView = convertView.findViewById(R.id.costCustomListView);
        TextView paymentCustomListView = convertView.findViewById(Integer.parseInt(String.valueOf(R.id.paymentCustomListView)));

        nameCustomListView.setText(listData.name);
        costCustomListView.setText(String.valueOf(listData.cost)+ " " +convertView.getContext().getString(R.string.currency));
        if (inHowManyDaysPayment==0) {
            paymentCustomListView.setText("Today");
            paymentCustomListView.setTextColor(Color.RED);
        }
        else if (inHowManyDaysPayment == 1) {
            paymentCustomListView.setText("Tomorrow");
            int orangeColor = getContext().getResources().getColor(R.color.orange);
            paymentCustomListView.setTextColor(orangeColor);
        }
        else if (inHowManyDaysPayment == 2) {
            paymentCustomListView.setText("payment in " + inHowManyDaysPayment + " days");
            int orangeColor = getContext().getResources().getColor(R.color.orange);
            paymentCustomListView.setTextColor(orangeColor);
        }
        else {
            paymentCustomListView.setText("payment in " + inHowManyDaysPayment + " days");
            paymentCustomListView.setTextColor(Color.BLACK);
        }

        return convertView;
    }
}

package com.example.subtracker;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

public class SummaryListAdapter extends ArrayAdapter<SummaryListData> {
    public SummaryListAdapter(@NonNull Context context, List<SummaryListData> objects) {
        super(context, R.layout.summary_list_view, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SummaryListData summaryListData = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.summary_list_view, parent, false);
        }

        TextView nameSummaryListView = convertView.findViewById(R.id.summaryNameCustomListView);
        TextView costSummaryListView = convertView.findViewById(R.id.summaryCostCustomListView);

        nameSummaryListView.setText(summaryListData.name);

        if (summaryListData.name.length()<20)
            nameSummaryListView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        else if (summaryListData.name.length()<30)
            nameSummaryListView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        else if (summaryListData.name.length()<40)
            nameSummaryListView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
        else
            nameSummaryListView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);

        costSummaryListView.setText(summaryListData.cost+ " " +convertView.getContext().getString(R.string.currency));

        if (summaryListData.cost<100)
            costSummaryListView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        else if (summaryListData.cost<1000)
            costSummaryListView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        else if (summaryListData.cost<100000)
            costSummaryListView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        else
            costSummaryListView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);

        return convertView;
    }
}

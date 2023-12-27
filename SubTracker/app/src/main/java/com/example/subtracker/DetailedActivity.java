package com.example.subtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.subtracker.databinding.ActivityDetailedBinding;

public class DetailedActivity extends AppCompatActivity {

    ActivityDetailedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        binding = ActivityDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        if(intent!=null){
            String name = intent.getStringExtra("name");
            Float price = intent.getFloatExtra("price",0.0f);
            int day = intent.getIntExtra("day of month",R.string.lorem_ipsum);
            int image = intent.getIntExtra("image", R.drawable.hbomax);
            binding.detailedNameTextView.setText(name);
            binding.detailedPriceTextView.setText("Price: " + Float.toString(price));
            if(day<29)
                binding.detailedDayTextView.setText("Payment: "+Integer.toString(day)+ " Every Month");
            else
                binding.detailedDayTextView.setText("Payment: "+Integer.toString(day)+ " Every Month\n" +
                        "(get more info about when your subscription is automatically renewed if you paying after 28 day of month, because not every month does have 29 day in month)");
            binding.detailedImageView.setImageResource(image);

        }
    }
}
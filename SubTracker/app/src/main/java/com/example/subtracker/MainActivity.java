package com.example.subtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    ArrayList<String> subList = new ArrayList<String>();
    ArrayList<Float> priceList = new ArrayList<Float>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        subList.add("obj1");
        subList.add("obj2");
        priceList.add(12.0f);
        priceList.add(34.0f);
    }

    public void onClickAdd(View view) {
        EditText editTextName = (EditText) findViewById(R.id.editTextName);//pobranie wartości editText z nazwą abonamentu
        EditText editTextPrice = (EditText) findViewById(R.id.editTextPrice);//pobranie wartości editText z ceną abonamentu
        subList.add(editTextName.getText().toString());//dodanie pobranych danych do listy z nazwami
        priceList.add(Float.valueOf(editTextPrice.getText().toString()));//dodanie pobranych danych do listy z cenami
        TextView txtViewName = (TextView) findViewById(R.id.textViewName);//inicjalizacja textView z abonamentami
        TextView txtViewPrice = (TextView) findViewById(R.id.textViewPrice);//inicjalizacja textView z cenami
        String prompt = "Abonamenty:\n";//inicjalizacja wartości aktualizującej komunikat o zawartości subList
        String prompt2 = "Ceny:\n";//inicjalizacja wartości aktualizującej komunikat o zawartości priceList
        //dodawanie elementów do textView
        for (String element : subList) {
            prompt+=element.toString()+"\n";
        }
        for (Float element : priceList) {
            prompt2+=element.toString()+"\n";
        }
        txtViewName.setText(prompt);//wyświetlenie listy z abonamentami
        txtViewPrice.setText(prompt2);//wyświetlenie listy z cenami
        editTextName.setText("");//wyczyszczenie editTextName
        editTextPrice.setText("");//wyczyszczenie editTextPrice
    }
}


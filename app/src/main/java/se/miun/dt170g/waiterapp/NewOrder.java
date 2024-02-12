package se.miun.dt170g.waiterapp;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;;


import java.util.ArrayList;

public class NewOrder extends AppCompatActivity {

    NumberPicker numberPicker = findViewById(R.id.gg);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        setTitle("Ny beställning");


        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(10);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });

    }






}
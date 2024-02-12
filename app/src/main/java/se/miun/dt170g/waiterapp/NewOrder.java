package se.miun.dt170g.waiterapp;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;;


import java.util.ArrayList;

public class NewOrder extends AppCompatActivity {

    private ArrayList<InputModel> inputModels = new ArrayList<>();;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        setTitle("Ny beställning");

        RecyclerView recyclerView = findViewById(R.id.InputRecycleView);

        setUpOrderInput();

        InputAdapter adapter = new InputAdapter(this, inputModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setUpOrderInput(){
        for (int i = 0; i < 10; i++) {
            inputModels.add(new InputModel("gg",312,0));
        }
    }





}
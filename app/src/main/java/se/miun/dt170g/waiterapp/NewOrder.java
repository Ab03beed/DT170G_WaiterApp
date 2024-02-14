package se.miun.dt170g.waiterapp;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.NumberPicker;

;


import java.util.ArrayList;

import se.miun.dt170g.waiterapp.adapters.InputAdapter;
import se.miun.dt170g.waiterapp.class_models.InputModel;

public class NewOrder extends AppCompatActivity {

    private ArrayList<InputModel> inputModels = new ArrayList<>();;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        int bordNr =  getIntent().getIntExtra("TableNr", 0);

        setTitle("Bord Nr: " + bordNr);

        RecyclerView recyclerView1 = findViewById(R.id.InputRecycleView1);
        RecyclerView recyclerView2 = findViewById(R.id.InputRecycleView2);
        RecyclerView recyclerView3 = findViewById(R.id.InputRecycleView3);

        setUpOrderInput();

        InputAdapter adapter = new InputAdapter(this, inputModels);

        recyclerView1.setAdapter(adapter);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));


        recyclerView2.setAdapter(adapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));


        recyclerView3.setAdapter(adapter);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));

        NumberPicker numberPicker = findViewById(R.id.itemCount);

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(100);



    }

    private void setUpOrderInput(){
        for (int i = 0; i < 10; i++) {
            inputModels.add(new InputModel("Item Name",312,0));

        }


    }





}
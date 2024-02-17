package se.miun.dt170g.waiterapp;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

;


import java.util.ArrayList;

import se.miun.dt170g.waiterapp.adapters.InputAdapter;
import se.miun.dt170g.waiterapp.class_models.InputModel;

public class NewOrder extends AppCompatActivity {

    private ArrayList<InputModel> inputModels = new ArrayList<>();;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        int bordNr =  getIntent().getIntExtra("TableNr", 0);

        setTitle("Bord Nr: " + bordNr);

        RecyclerView recyclerView1 = findViewById(R.id.ForRV);
        RecyclerView recyclerView2 = findViewById(R.id.HuvudRV);
        RecyclerView recyclerView3 = findViewById(R.id.EfterRV);
        RecyclerView recyclerView4 = findViewById(R.id.DrinksRV);

        setUpOrderInput();

        InputAdapter adapter = new InputAdapter(this, inputModels);

        recyclerView1.setAdapter(adapter);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));


        recyclerView2.setAdapter(adapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));


        recyclerView3.setAdapter(adapter);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));

        recyclerView4.setAdapter(adapter);
        recyclerView4.setLayoutManager(new LinearLayoutManager(this));

        //numberPicker = findViewById(R.id.numberPicker);
        





    }

    private void setUpOrderInput(){
        for (int i = 0; i < 10; i++) {

            if(i == 0)
                inputModels.add(new InputModel("kebab",120, findViewById(R.id.FoodCount)));

            else if(i == 2)
                inputModels.add(new InputModel("pizza",99, findViewById(R.id.FoodCount)));
            else
                inputModels.add(new InputModel("Item Name",312, findViewById(R.id.FoodCount)));


        }


    }





}
package se.miun.dt170g.waiterapp;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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

        setTitle("Ny beställning");

        RecyclerView recyclerView = findViewById(R.id.InputRecycleView);

        setUpOrderInput();

        InputAdapter adapter = new InputAdapter(this, inputModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Spinner tableDropDown = findViewById(R.id.tabels);

        ArrayAdapter<CharSequence> dropDownAdapter = ArrayAdapter.createFromResource(this, R.array.tablesID, android.R.layout.simple_spinner_item);
        dropDownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        tableDropDown.setAdapter(dropDownAdapter);

    }

    private void setUpOrderInput(){
        for (int i = 0; i < 10; i++) {
            inputModels.add(new InputModel("Item Name",312,0));

        }


    }





}
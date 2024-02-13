package se.miun.dt170g.waiterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import se.miun.dt170g.waiterapp.adapters.ItemsAdapter;
import se.miun.dt170g.waiterapp.class_models.ItemModel;

public class OrderDetails extends AppCompatActivity {

    ArrayList<ItemModel> itemModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        int tableNr = getIntent().getIntExtra("TableNr", 0);

        setTitle("Bord Nr: " + tableNr);

        RecyclerView recyclerView = findViewById(R.id.ItemsRecycleView);

        setUpOrderItems();

        ItemsAdapter adapter = new ItemsAdapter(this, itemModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    private void setUpOrderItems(){
        String[] itemNames =  getResources().getStringArray(R.array.itemNames);
        String[] itemPrices =  getResources().getStringArray(R.array.itemPrices);

        for (int i = 0; i < itemNames.length; i++) {
            itemModels.add(new ItemModel(itemNames[i], Integer.parseInt(itemPrices[i])));


        }
    }

}
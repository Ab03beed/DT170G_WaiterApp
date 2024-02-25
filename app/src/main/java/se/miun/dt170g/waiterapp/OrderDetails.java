package se.miun.dt170g.waiterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.miun.dt170g.waiterapp.adapters.ItemsAdapter;
import se.miun.dt170g.waiterapp.class_models.ItemModel;
import se.miun.dt170g.waiterapp.class_models.TableModel;
import se.miun.dt170g.waiterapp.fetch.FetchData;
import se.miun.dt170g.waiterapp.fetch.Retro;

public class OrderDetails extends AppCompatActivity {

    ArrayList<ItemModel> itemModels = new ArrayList<>();

    private Retro retrofit = new Retro();
    private FetchData fetchData = retrofit.getRetrofit().create(FetchData.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        int tableNr = getIntent().getIntExtra("TableNr", 0);

        setTitle("Bord Nr: " + tableNr);

        RecyclerView recyclerView = findViewById(R.id.ItemsRV);

        setUpOrderItems();

        ItemsAdapter adapter = new ItemsAdapter(this, itemModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

         Button insertBtn = (Button) findViewById(R.id.EndOrder);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endOrder(v);
            }
        });


    }

    private void setUpOrderItems(){
        String[] itemNames =  getResources().getStringArray(R.array.itemNames);
        String[] itemPrices =  getResources().getStringArray(R.array.itemPrices);

        for (int i = 0; i < itemNames.length; i++) {
            itemModels.add(new ItemModel(itemNames[i], Integer.parseInt(itemPrices[i])));


        }
    }


    public void endOrder(View view){
        //Table data
        int tableNr = getIntent().getIntExtra("TableNr", 0);
        int tableSession = getIntent().getIntExtra("TableSession", 0);
        int tableSize = getIntent().getIntExtra("TableSize", 0);

        //Delete from the API
        //...


        //Update table status
        updateTableStatus(tableSession, new TableModel(tableSession, tableNr, tableSize, "Ledig"));

        //Go back to the MainActivity
        Intent intent = new Intent(OrderDetails.this, MainActivity.class);
        startActivity(intent);

    }


    public void updateTableStatus(int tableId, TableModel tableModel){

        Call<Void> call = fetchData.updateTableStatus(tableId, tableModel);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
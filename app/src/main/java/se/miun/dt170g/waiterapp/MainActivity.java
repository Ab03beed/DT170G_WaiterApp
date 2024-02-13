package se.miun.dt170g.waiterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import se.miun.dt170g.waiterapp.adapters.OrdersAdapter;
import se.miun.dt170g.waiterapp.adapters.OrdersInterface;
import se.miun.dt170g.waiterapp.class_models.OrderModel;

public class MainActivity extends AppCompatActivity implements OrdersInterface {

    ArrayList<OrderModel> orderModels = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Beställningar");

        RecyclerView recyclerView = findViewById(R.id.OrdersRecycleView);

        setUpOrderModels();

        OrdersAdapter adapter = new OrdersAdapter(this, orderModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        /*RecyclerView recyclerView = findViewById(R.id.OrdersRecycleView);

        setUpOrderItems();

        ItemAdapter adapter = new ItemAdapter(this, itemModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/




    }


    public void newOrder(View v){

        Intent i = new Intent(this, NewOrder.class);
        startActivity(i);
    }


    private void setUpOrderModels(){
        String[] orders =  getResources().getStringArray(R.array.Orders);
        String[] status =  getResources().getStringArray(R.array.Status);


        for (int i = 0; i < orders.length; i++) {
            orderModels.add(new OrderModel(i, i,status[i], orders[i]));
        }
    }




    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(this, OrderDetails.class);

        i.putExtra("TableNr",position);

        startActivity(i);
    }
}
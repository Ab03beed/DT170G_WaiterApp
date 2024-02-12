package se.miun.dt170g.waiterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

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


        // Inflate the card template
        LayoutInflater inflater = getLayoutInflater();
        View cardTemplate = inflater.inflate(R.layout.input_template, null);

        // Add the inflated card template to your main layout
        RelativeLayout mainLayout = findViewById(R.id.); // Replace with the actual ID of your main layout
        mainLayout.addView(cardTemplate);



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
        Intent i = new Intent(this, CurrentOrder.class);

        i.putExtra("TableNr",position);

        startActivity(i);
    }
}
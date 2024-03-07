package se.miun.dt170g.waiterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.miun.dt170g.waiterapp.adapters.ItemsAdapter;
import se.miun.dt170g.waiterapp.adapters.ItemsInterface;
import se.miun.dt170g.waiterapp.adapters.TablesInterface;
import se.miun.dt170g.waiterapp.class_models.ALaCarteModel;
import se.miun.dt170g.waiterapp.class_models.DrinkModel;
import se.miun.dt170g.waiterapp.class_models.ItemModel;
import se.miun.dt170g.waiterapp.class_models.OrderDTO;
import se.miun.dt170g.waiterapp.class_models.TableModel;
import se.miun.dt170g.waiterapp.fetch.FetchData;
import se.miun.dt170g.waiterapp.fetch.Retro;

public class OrderDetails extends AppCompatActivity implements ItemsInterface {

    ArrayList<ItemModel> itemModels = new ArrayList<>();

    private Retro retrofit = new Retro();
    private FetchData fetchData = retrofit.getRetrofit().create(FetchData.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        //Sett the tableNr to the header.
        TableModel table = (TableModel) getIntent().getSerializableExtra("Table");
        setTitle("Bord Nr: " + table.getTableNumber());


        RecyclerView orderDetailsRV = findViewById(R.id.ItemsRV);

        //SetUp order details.
        SetUpOrderDetails(orderDetailsRV);


        Button insertBtn = (Button) findViewById(R.id.Add);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddItems(v);
            }
        });


        Button deleteBtn = (Button) findViewById(R.id.EndOrder);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endOrder(v);
            }
        });
    }

    public void SetUpOrderDetails(RecyclerView orderDetailsRV){
        //Get the order data
        OrderDTO orderDTO = (OrderDTO) getIntent().getSerializableExtra("orderDTO");

        ArrayList<ALaCarteModel> foods = new ArrayList<>();
        ArrayList<DrinkModel> drinks = new ArrayList<>();

        if(orderDTO != null){
            foods = orderDTO.getFoods();
            drinks = orderDTO.getDrinks();
        }
        //Loop through the arrays to add the orderDetails into itemModels array.
        for (int i = 0; i < foods.size(); i++) {
            itemModels.add(new ItemModel(foods.get(i).getaLaCarteID(), foods.get(i).getName(), foods.get(i).getPrice()));
        }

        for (int i = 0; i < drinks.size(); i++) {
            itemModels.add(new ItemModel(drinks.get(i).getDrink_id() ,drinks.get(i).getName(), drinks.get(i).getPrice()));
        }

        //Set the adapter to view the items.adapter
        ItemsAdapter adapter = new ItemsAdapter(this, itemModels, OrderDetails.this);
        orderDetailsRV.setAdapter(adapter);
        orderDetailsRV.setLayoutManager(new LinearLayoutManager(this));
    }

    public void AddItems(View view){
        //Table data
        TableModel tableModel = (TableModel) getIntent().getSerializableExtra("Table");
        //Order data
        OrderDTO orderDTO = (OrderDTO) getIntent().getSerializableExtra("orderDTO");

        if(orderDTO == null)
            orderDTO = new OrderDTO();

        //Go back to the MainActivity
        Intent intent = new Intent(OrderDetails.this, NewOrder.class);


        intent.putExtra("oldOrder", orderDTO);
        intent.putExtra("Table", tableModel);
        intent.putExtra("isNew", false);

        startActivity(intent);
    }

    public void endOrder(View view){
        //Table data
        TableModel tableModel = (TableModel) getIntent().getSerializableExtra("Table");

        //Order data
        OrderDTO orderDTO = (OrderDTO) getIntent().getSerializableExtra("orderDTO");
        orderDTO.setOrderStatus(false);

        //Update the order status in the API
        updateOrder(orderDTO.getOrder_ID(), orderDTO);

        //Update table status
        updateTable(tableModel.getTableNumber(), new TableModel(tableModel.getTableNumber(), tableModel.getTableSize(), "Free"));
        //updateTable(tableModel.getSessionId(), new TableModel(tableModel.getSessionId(), tableModel.getTableNumber(), tableModel.getTableSize(), "Free"));


        //Go back to the MainActivity
        Intent intent = new Intent(OrderDetails.this, MainActivity.class);
        startActivity(intent);
    }

    public void updateTable(int tableId, TableModel tableModel){

        Call<Void> call = fetchData.updateTable(tableId, tableModel);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void updateOrder(int orderId, OrderDTO newOrder){

        Call<Void> call = fetchData.updateOrder(orderId, newOrder);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    //To delete item from the API.
    @Override
    public void deleteOnClickItem(int position) {
        boolean deleted = false;

        Toast.makeText(getApplicationContext(), "Pos: " + position , Toast.LENGTH_SHORT).show();

        //Order data
        OrderDTO newOrderDTO = (OrderDTO) getIntent().getSerializableExtra("orderDTO");

        ArrayList<ALaCarteModel> foods = newOrderDTO.getFoods();
        ArrayList<DrinkModel> drinks = newOrderDTO.getDrinks();


        //Check if a ALaCarte is deleted
        for (int j = 0; j < foods.size(); j++) {
            if(foods.get(j).getaLaCarteID() == itemModels.get(position).getId() &&
                    foods.get(j).getName().equals(itemModels.get(position).getItemName())){
                foods.remove(j);
                itemModels.remove(position);
                deleted = true;
                break;
            }
        }

        //Check if Drink should be deleted instead
        if(!deleted)
            for (int j = 0; j < drinks.size(); j++) {
                if(drinks.get(j).getDrink_id() == itemModels.get(position).getId() &&
                        drinks.get(j).getName().equals(itemModels.get(position).getItemName())){
                    drinks.remove(j);
                    itemModels.remove(position);
                    break;
                }
            }

        //update the order in the API.
        updateOrder(newOrderDTO.getOrder_ID(), newOrderDTO);

        //Refresh the orderDetails.
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
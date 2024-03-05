package se.miun.dt170g.waiterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.miun.dt170g.waiterapp.adapters.TablesAdapter;
import se.miun.dt170g.waiterapp.adapters.TablesInterface;
import se.miun.dt170g.waiterapp.class_models.DrinkModel;
import se.miun.dt170g.waiterapp.class_models.OrderDTO;
import se.miun.dt170g.waiterapp.class_models.TableModel;
import se.miun.dt170g.waiterapp.fetch.FetchData;
import se.miun.dt170g.waiterapp.fetch.Retro;

public class MainActivity extends AppCompatActivity implements TablesInterface {

    private ArrayList<TableModel> tableModels = new ArrayList<>();
    private ArrayList<OrderDTO> activeOrders = new ArrayList<>();

    private Retro retrofit = new Retro();
    private FetchData fetchData = retrofit.getRetrofit().create(FetchData.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Bord");

        fetchActiveOrders();



    }

    public void fetchActiveOrders(){
        Call<ArrayList<OrderDTO>> call = fetchData.getActiveOrders();

        call.enqueue(new Callback<ArrayList<OrderDTO>>() {
            @Override
            public void onResponse(Call<ArrayList<OrderDTO>> call, Response<ArrayList<OrderDTO>> response) {
                if (response.isSuccessful()) {
                    //Fill the ArrayList with active orders from the API.
                    activeOrders = response.body();

                    RecyclerView recyclerView = findViewById(R.id.TablesRV);
                    fetchTables(recyclerView, activeOrders);
                    
                }else{
                    Log.d("res", "fetchActiveOrders " + String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<OrderDTO>> call, Throwable t) {
                Log.d("res", "Failure fetchActiveOrders " + t.getMessage());
            }
        });
    }

    public void fetchTables(RecyclerView recyclerView, ArrayList<OrderDTO> activeOrders){
        Call<ArrayList<TableModel>> call = fetchData.getTables();

        call.enqueue(new Callback<ArrayList<TableModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TableModel>> call, Response<ArrayList<TableModel>> response) {
                if (response.isSuccessful()) {
                    //Fill the ArrayList with tables from the API.
                    tableModels = response.body();

                    TablesAdapter adapter = new TablesAdapter(MainActivity.this, tableModels, activeOrders, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                }else{
                    Log.d("res", "fetchTables "+String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TableModel>> call, Throwable t) {
                Log.d("res", "Failure fetchTables " + t.getMessage());
            }
        });
    }

    @Override
    public void onTableClick(int position) {
        //Checks if the table is empty or not, 0 refers to empty.
        if(tableModels.get(position).getTableStatus().equals("Free") || tableModels.get(position).getTableStatus().equals("Reserved")){
            Intent i = new Intent(this, NewOrder.class);

            //send table info
            i.putExtra("Table", tableModels.get(position));

            startActivity(i);

        }else{
            Intent intent = new Intent(this, OrderDetails.class);

            //Send table info
            intent.putExtra("Table", tableModels.get(position));

            //send order info
            for (int i = 0; i < activeOrders.size(); i++) {
                if(activeOrders.get(i).getRestaurantTableId() == tableModels.get(position).getTableNumber()){
                    intent.putExtra("orderDTO", activeOrders.get(i));
                    break;
                }
            }

            startActivity(intent);
        }
    }
}
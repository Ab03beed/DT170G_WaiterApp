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
import se.miun.dt170g.waiterapp.class_models.TableModel;
import se.miun.dt170g.waiterapp.fetch.FetchData;
import se.miun.dt170g.waiterapp.fetch.Retro;

public class MainActivity extends AppCompatActivity implements TablesInterface {

    private ArrayList<TableModel> tableModels = new ArrayList<>();

    private final String WS_HOST = "http://192.168.0.101:8080/projektDT170G-1.0-SNAPSHOT/api/";
    private Retro retrofit = new Retro(WS_HOST);
    private FetchData fetchData = retrofit.getRetrofit().create(FetchData.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Bord");

        RecyclerView recyclerView = findViewById(R.id.TablesRV);

        fetchTables(recyclerView);

    }

    public void fetchTables(RecyclerView recyclerView){
        Call<ArrayList<TableModel>> call = fetchData.getTables();

        call.enqueue(new Callback<ArrayList<TableModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TableModel>> call, Response<ArrayList<TableModel>> response) {
                if (response.isSuccessful()) {
                    //Fill the ArrayList with tables from the API.
                    tableModels = response.body();

                    TablesAdapter adapter = new TablesAdapter(MainActivity.this, tableModels, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                }else{
                    Log.d("Response", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TableModel>> call, Throwable t) {
                Log.d("Response", t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        //Checks if the table is empty or not, 0 refers to empty.
        if(tableModels.get(position).getTableStatus() == 0){
            Intent i = new Intent(this, NewOrder.class);
            i.putExtra("TableSession",tableModels.get(position).getSessionId());
            i.putExtra("TableNr",tableModels.get(position).getTableNumber());
            i.putExtra("TableSize",tableModels.get(position).getTableSize());
            startActivity(i);

        }else{
            Intent i = new Intent(this, OrderDetails.class);
            i.putExtra("TableSession",tableModels.get(position).getSessionId());
            i.putExtra("TableNr",tableModels.get(position).getTableNumber());
            i.putExtra("TableSize",tableModels.get(position).getTableSize());
            startActivity(i);
        }
    }
}
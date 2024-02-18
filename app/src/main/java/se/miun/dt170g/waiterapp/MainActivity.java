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


        Button b = (Button) findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test(v);
            }
        });


    }

    public void test(View view){

        Call<Void> call = fetchData.addDrink(new DrinkModel(0,"XXL","desc",132));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("gg","Successful!");
                } else {
                    Log.d("gg","NotSuccessful!");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("gg","Failure!");
            }
        });


    }

    public void fetchTables(RecyclerView recyclerView){

        Call<ArrayList<TableModel>> call = fetchData.getTables();

        call.enqueue(new Callback<ArrayList<TableModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TableModel>> call, Response<ArrayList<TableModel>> response) {
                if (response.isSuccessful()) {

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
            i.putExtra("TableNr",position+1);
            startActivity(i);

        }else{

            Intent i = new Intent(this, OrderDetails.class);
            i.putExtra("TableNr",position+1);
            startActivity(i);

        }




    }
}
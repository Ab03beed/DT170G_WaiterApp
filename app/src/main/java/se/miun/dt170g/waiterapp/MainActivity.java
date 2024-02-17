package se.miun.dt170g.waiterapp;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.miun.dt170g.waiterapp.adapters.TablesAdapter;
import se.miun.dt170g.waiterapp.adapters.TablesInterface;
import se.miun.dt170g.waiterapp.class_models.ALaCarteItem;
import se.miun.dt170g.waiterapp.class_models.TableItem;

public class MainActivity extends AppCompatActivity implements TablesInterface {

    private final String WS_HOST = "http://192.168.0.101:8080/projektDT170G-1.0-SNAPSHOT/api/";

    private ArrayList<TableItem> tableItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Bord");

        RecyclerView recyclerView = findViewById(R.id.TablesRV);

        fetchALaCarteItems(recyclerView);


    }


    public void fetchALaCarteItems(RecyclerView recyclerView){

        //Creating Retrofit obj.
        Retro retrofit = new Retro(WS_HOST);


        FetchData fetchData = retrofit.getRetrofit().create(FetchData.class);

        Call<ArrayList<TableItem>> call = fetchData.getTables();

        call.enqueue(new Callback<ArrayList<TableItem>>() {
            @Override
            public void onResponse(Call<ArrayList<TableItem>> call, Response<ArrayList<TableItem>> response) {
                if (response.isSuccessful()) {

                    tableItems = response.body();


                    TablesAdapter adapter = new TablesAdapter(MainActivity.this, tableItems, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


                }else{
                    Log.d("Response", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TableItem>> call, Throwable t) {
                Log.d("Response", t.getMessage());
            }
        });





    }


    public void newOrder(View v){

        Intent i = new Intent(this, NewOrder.class);
        startActivity(i);
    }



    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(this, NewOrder.class);

        i.putExtra("TableNr",position+1);

        startActivity(i);
    }
}
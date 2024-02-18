package se.miun.dt170g.waiterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.miun.dt170g.waiterapp.adapters.DrinkAdapter;
import se.miun.dt170g.waiterapp.adapters.InputAdapter;
import se.miun.dt170g.waiterapp.adapters.ItemsAdapter;
import se.miun.dt170g.waiterapp.adapters.TablesAdapter;
import se.miun.dt170g.waiterapp.class_models.ALaCarteItem;
import se.miun.dt170g.waiterapp.class_models.DrinkModel;
import se.miun.dt170g.waiterapp.class_models.InputModel;
import se.miun.dt170g.waiterapp.class_models.TableItem;
import se.miun.dt170g.waiterapp.fetch.FetchData;
import se.miun.dt170g.waiterapp.fetch.Retro;

public class NewOrder extends AppCompatActivity {

    private final String WS_HOST = "http://192.168.0.104:8080/projektDT170G-1.0-SNAPSHOT/api/";

    private ArrayList<DrinkModel> drinksItems = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        int bordNr =  getIntent().getIntExtra("TableNr", 0);

        setTitle("Bord Nr: " + bordNr);

        RecyclerView recyclerView = findViewById(R.id.DrinksRV);
        fetchDrinks(recyclerView);

    }

    public void fetchDrinks(RecyclerView recyclerView){

        //Creating Retrofit obj.
        Retro retrofit = new Retro(WS_HOST);

        FetchData fetchData = retrofit.getRetrofit().create(FetchData.class);

        Call<ArrayList<DrinkModel>> call = fetchData.getDrinks();

        call.enqueue(new Callback<ArrayList<DrinkModel>>() {
            @Override
            public void onResponse(Call<ArrayList<DrinkModel>> call, Response<ArrayList<DrinkModel>> response) {
                if (response.isSuccessful()) {

                    drinksItems = response.body();

                    DrinkAdapter adapter = new DrinkAdapter(NewOrder.this, drinksItems);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(NewOrder.this));

                }else{
                    Log.d("Response", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DrinkModel>> call, Throwable t) {
                Log.d("Response", t.getMessage());
            }
        });

    }

}
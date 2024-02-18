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
import se.miun.dt170g.waiterapp.adapters.InputAdapter;
import se.miun.dt170g.waiterapp.class_models.ALaCarteModel;
import se.miun.dt170g.waiterapp.class_models.DrinkModel;
import se.miun.dt170g.waiterapp.class_models.InputModel;
import se.miun.dt170g.waiterapp.fetch.FetchData;
import se.miun.dt170g.waiterapp.fetch.Retro;

public class NewOrder extends AppCompatActivity {
    private ArrayList<InputModel> inputFor = new ArrayList<>();
    private ArrayList<InputModel> inputEfter = new ArrayList<>();
    private ArrayList<InputModel> inputHuvud = new ArrayList<>();
    private ArrayList<InputModel> inputDrinks = new ArrayList<>();

    private ArrayList<ALaCarteModel> aLaCarteModels = new ArrayList<>();
    private ArrayList<DrinkModel> drinkModels = new ArrayList<>();

    private final String WS_HOST = "http://192.168.0.101:8080/projektDT170G-1.0-SNAPSHOT/api/";
    private Retro retrofit = new Retro(WS_HOST);
    private FetchData fetchData = retrofit.getRetrofit().create(FetchData.class);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        //Set the Activity title, that show the specific table number.
        int bordNr = getIntent().getIntExtra("TableNr", 0);
        setTitle("Bord Nr: " + bordNr);

        //RecyclerView for each of the types
        RecyclerView forRV = findViewById(R.id.ForRV);
        RecyclerView huvudRV = findViewById(R.id.HuvudRV);
        RecyclerView efterRV = findViewById(R.id.EfterRV);
        RecyclerView drinksRV = findViewById(R.id.DrinksRV);

        //Calling the fetch methods
        fetchA_LA_CARTE_ITEMS(forRV, huvudRV, efterRV);
        fetchDrinks(drinksRV);

    }

    public void fetchA_LA_CARTE_ITEMS(RecyclerView forRV, RecyclerView huvudRV, RecyclerView efterRV){

        Call<ArrayList<ALaCarteModel>> call = fetchData.getA_LA_CARTE_ITEMS();

        call.enqueue(new Callback<ArrayList<ALaCarteModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ALaCarteModel>> call, Response<ArrayList<ALaCarteModel>> response) {
                if (response.isSuccessful()) {
                    //Fill the array with ALaCarteModel items from response.
                    aLaCarteModels = response.body();

                    //Check the type of the aLaCarte and add it to the right ArrayList.
                    for (ALaCarteModel item: aLaCarteModels ){
                        if( item.getType().equals("förrätt")){
                            inputFor.add(new InputModel(item.getName(), item.getPrice(), findViewById(R.id.FoodCount)));
                        }else if(item.getType().equals("huvudrätt")){
                            inputHuvud.add(new InputModel(item.getName(), item.getPrice(), findViewById(R.id.FoodCount)));
                        }else{
                            inputEfter.add(new InputModel(item.getName(), item.getPrice(), findViewById(R.id.FoodCount)));
                        }
                        Log.d("gg", item.getType());
                    }

                    //Creating adapters for each inputArray.
                    InputAdapter forAdapter = new InputAdapter(NewOrder.this, inputFor);
                    forRV.setAdapter(forAdapter);
                    forRV.setLayoutManager(new LinearLayoutManager(NewOrder.this));

                    InputAdapter huvudAdapter = new InputAdapter(NewOrder.this, inputHuvud);
                    huvudRV.setAdapter(huvudAdapter);
                    huvudRV.setLayoutManager(new LinearLayoutManager(NewOrder.this));

                    InputAdapter efterAdapter = new InputAdapter(NewOrder.this, inputEfter);
                    efterRV.setAdapter(efterAdapter);
                    efterRV.setLayoutManager(new LinearLayoutManager(NewOrder.this));

                }else{
                    Log.d("Response", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ALaCarteModel>> call, Throwable t) {
                Log.d("Response", t.getMessage());
            }
        });

    }

    public void fetchDrinks(RecyclerView drinksRV){
        Call<ArrayList<DrinkModel>> call = fetchData.getDrinks();

        call.enqueue(new Callback<ArrayList<DrinkModel>>() {
            @Override
            public void onResponse(Call<ArrayList<DrinkModel>> call, Response<ArrayList<DrinkModel>> response) {
                if (response.isSuccessful()) {
                    //Fill the array with DrinkModel from the response.
                    drinkModels = response.body();

                    //Add the drinks to the inputDrinks ArrayList.
                    for (DrinkModel item: drinkModels){
                        inputDrinks.add(new InputModel(item.getName(), item.getPrice(),findViewById(R.id.FoodCount)));
                    }

                    //Creating adapter for the inputDrinks ArrayList.
                    InputAdapter drinksAdapter = new InputAdapter(NewOrder.this, inputDrinks);
                    drinksRV.setAdapter(drinksAdapter);
                    drinksRV.setLayoutManager(new LinearLayoutManager(NewOrder.this));

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
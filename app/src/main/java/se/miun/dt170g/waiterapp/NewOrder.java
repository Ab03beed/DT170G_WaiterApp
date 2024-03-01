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
import android.widget.EditText;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.miun.dt170g.waiterapp.adapters.InputAdapter;
import se.miun.dt170g.waiterapp.class_models.ALaCarteModel;
import se.miun.dt170g.waiterapp.class_models.DrinkModel;
import se.miun.dt170g.waiterapp.class_models.InputModel;
import se.miun.dt170g.waiterapp.class_models.OrderDTO;
import se.miun.dt170g.waiterapp.class_models.TableModel;
import se.miun.dt170g.waiterapp.fetch.FetchData;
import se.miun.dt170g.waiterapp.fetch.Retro;


public class NewOrder extends AppCompatActivity {
    private static int orderID = 0;

    private ArrayList<InputModel> inputFor = new ArrayList<>();
    private ArrayList<InputModel> inputEfter = new ArrayList<>();
    private ArrayList<InputModel> inputHuvud = new ArrayList<>();
    private ArrayList<InputModel> inputDrinks = new ArrayList<>();

    private ArrayList<ALaCarteModel> aLaCarteModels = new ArrayList<>();
    private ArrayList<DrinkModel> drinkModels = new ArrayList<>();

    private Retro retrofit = new Retro();
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

        Button insertBtn = (Button) findViewById(R.id.SendBtn);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertOrder(v);
            }
        });


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
                    for (ALaCarteModel item: aLaCarteModels){
                        if( item.getType().equals("förrätt")){
                            inputFor.add(new InputModel(item.getaLaCarteID(), item.getPrice(), item.getName(), item.getType(), item.getDescription()));
                        }else if(item.getType().equals("huvudrätt")){
                            inputHuvud.add(new InputModel(item.getaLaCarteID(), item.getPrice(), item.getName(), item.getType(), item.getDescription()));
                        }else{
                            inputEfter.add(new InputModel(item.getaLaCarteID(), item.getPrice(), item.getName(), item.getType(), item.getDescription()));
                        }
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
                    Log.d("Fetch response", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ALaCarteModel>> call, Throwable t) {
                Log.d("newOrder response", t.getMessage());
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
                        inputDrinks.add(new InputModel(item.getDrink_id(), item.getPrice(),  item.getName(), "", ""));
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


    public void insertOrder(View view){
        OrderDTO newOrder = new OrderDTO();
        //Table data
        int tableId = getIntent().getIntExtra("TableNr", 0);
        int tableSession = getIntent().getIntExtra("TableSession", 0);
        int tableSize = getIntent().getIntExtra("TableSize", 0);

        //Comment
        EditText textInput = findViewById(R.id.CommentIn);
        String comment = String.valueOf(textInput.getText());

        for (int i = 0; i < inputFor.size(); i++) {
            int count = inputFor.get(i).getCount();
            while(count != 0){
                newOrder.addFood(new ALaCarteModel(inputFor.get(i).getId(), inputFor.get(i).getPrice(), inputFor.get(i).getItemName(), inputFor.get(i).getType(), inputFor.get(i).getDescription()));
                count--;
            }
        }
        for (int i = 0; i < inputHuvud.size(); i++) {
            int count = inputHuvud.get(i).getCount();
            while(count != 0){
                newOrder.addFood(new ALaCarteModel(inputHuvud.get(i).getId(), inputHuvud.get(i).getPrice(), inputHuvud.get(i).getItemName(), inputHuvud.get(i).getType(), inputHuvud.get(i).getDescription()));
                count--;
            }
        }
        for (int i = 0; i < inputEfter.size(); i++) {
            int count = inputEfter.get(i).getCount();
            while(count != 0){
                newOrder.addFood(new ALaCarteModel(inputEfter.get(i).getId(), inputEfter.get(i).getPrice(), inputEfter.get(i).getItemName(), inputEfter.get(i).getType(), inputEfter.get(i).getDescription()));
                count--;
            }
        }
        for (int i = 0; i < inputDrinks.size(); i++) {
            int count = inputDrinks.get(i).getCount();
            while(count != 0){
                newOrder.addDrink(new DrinkModel(inputDrinks.get(i).getId(), inputDrinks.get(i).getItemName(), inputDrinks.get(i).getDescription(), inputDrinks.get(i).getPrice()));
                count--;
            }
        }

        //Setting the order details
        newOrder.setOrder_ID(0);
        newOrder.setStatusDessert("Skickat");
        newOrder.setStatusMain("Skickat");
        newOrder.setStatusAppetizer("Skickat");
        newOrder.setRestaurantTableId(tableId);
        newOrder.setComment(comment);
        newOrder.setOrderStatus(true);

        //Posting the order to the API.
        Call<Void> call = fetchData.addOrder(newOrder);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    //Change table status
                    updateTableStatus(tableSession, new TableModel(tableSession,tableId,tableSize, "Aktiv"));

                    //Go back to the MainActivity
                    Intent intent = new Intent(NewOrder.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Log.d("insert Response","NotSuccessful!");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("insert Response","Failure!");
            }
        });
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
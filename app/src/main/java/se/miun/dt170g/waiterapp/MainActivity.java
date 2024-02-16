package se.miun.dt170g.waiterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.miun.dt170g.waiterapp.adapters.TablesAdapter;
import se.miun.dt170g.waiterapp.adapters.TablesInterface;
import se.miun.dt170g.waiterapp.class_models.ALaCarteItem;
import se.miun.dt170g.waiterapp.class_models.TableModel;

public class MainActivity extends AppCompatActivity implements TablesInterface {

    ArrayList<TableModel> tableModels = new ArrayList<>();

    private final String WS_HOST = "http://192.168.0.101:8080/projektDT170G-1.0-SNAPSHOT/api/";
    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0";


    private ArrayList<ALaCarteItem> ALaCarteItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setTitle("Bord");

        /*RecyclerView recyclerView = findViewById(R.id.TablesRV);

        setUpOrderModels();

        TablesAdapter adapter = new TablesAdapter(this, tableModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/

        fetchALaCarteItems();



    }


    public void fetchALaCarteItems(){




        //Creating Retrofit obj.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WS_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();




        FetchData fetchData = retrofit.create(FetchData.class);

        Call<ArrayList<ALaCarteItem>> call = fetchData.get_drinks();

        call.enqueue(new Callback<ArrayList<ALaCarteItem>>() {
            @Override
            public void onResponse(Call<ArrayList<ALaCarteItem>> call, Response<ArrayList<ALaCarteItem>> response) {
                if (response.isSuccessful()) {
                    Log.d("gg", "gggggggggggggggggggggggggggggggggggggggg");
                }else{
                    Log.d("gg", "not Successful");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ALaCarteItem>> call, Throwable t) {
                Log.d("gg", "response faluire");
                Log.d("gg", t.getMessage());

            }
        });




    }


    public void newOrder(View v){

        Intent i = new Intent(this, NewOrder.class);
        startActivity(i);
    }



    private void setUpOrderModels(){

        String[] tables = {"Bord Nr: 1", "Bord Nr: 2", "Bord Nr: 3", "Bord Nr: 4", "Bord Nr: 5", "Bord Nr: 6", "Bord Nr: 7", "Bord Nr: 8"};
        int[] tablesStatus = {1, 0, 1, 0, 1, 0, 1, 0};

        for (int i = 1; i <= tables.length; i++) {
            tableModels.add(new TableModel(i,tablesStatus[i-1], tables[i-1]));
        }
    }



    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(this, NewOrder.class);

        i.putExtra("TableNr",position+1);

        startActivity(i);
    }
}
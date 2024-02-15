package se.miun.dt170g.waiterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import se.miun.dt170g.waiterapp.adapters.TablesAdapter;
import se.miun.dt170g.waiterapp.adapters.TablesInterface;
import se.miun.dt170g.waiterapp.class_models.TableModel;

public class MainActivity extends AppCompatActivity implements TablesInterface {

    ArrayList<TableModel> tableModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setTitle("Bord");

        RecyclerView recyclerView = findViewById(R.id.TablesRV);

        setUpOrderModels();

        TablesAdapter adapter = new TablesAdapter(this, tableModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



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
package se.miun.dt170g.waiterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Beställningar");
    }


    public void newOrder(View v){

        Intent i = new Intent(this, NewOrder.class);
        startActivity(i);
    }


}
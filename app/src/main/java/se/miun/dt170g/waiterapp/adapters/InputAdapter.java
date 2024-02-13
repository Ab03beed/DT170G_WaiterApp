package se.miun.dt170g.waiterapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import se.miun.dt170g.waiterapp.R;
import se.miun.dt170g.waiterapp.class_models.InputModel;

public class InputAdapter extends RecyclerView.Adapter<InputAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<InputModel> inputModels;

    public InputAdapter(Context context, ArrayList<InputModel> inputModels){
        this.context = context;
        this.inputModels = inputModels;
    }


    @NonNull
    @Override
    public InputAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.input_template, parent, false);

        return new InputAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InputAdapter.MyViewHolder holder, int position) {
        holder.itemName.setText(inputModels.get(position).getItemName());
        holder.price.setText("Pris: " + String.valueOf(inputModels.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return inputModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView itemName, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.ItemName);
            price = itemView.findViewById(R.id.price);
        }
    }

}



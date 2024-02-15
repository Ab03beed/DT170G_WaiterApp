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
import se.miun.dt170g.waiterapp.class_models.ItemModel;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ItemModel> itemModels;

    public ItemsAdapter(Context context, ArrayList<ItemModel> itemModels){
        this.context = context;
        this.itemModels = itemModels;
    }

    @NonNull
    @Override
    public ItemsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_template, parent, false);

        return new ItemsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.MyViewHolder holder, int position) {

        holder.itemName.setText(itemModels.get(position).getItemName());
        holder.price.setText("Pris: " + String.valueOf(itemModels.get(position).getPrice()));
    }

    @Override
    public int getItemCount() { return itemModels.size(); }



    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView itemName, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.FoodName_item);
            price = itemView.findViewById(R.id.FoodPrice_input);

        }
    }
}

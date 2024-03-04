package se.miun.dt170g.waiterapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import se.miun.dt170g.waiterapp.R;

import se.miun.dt170g.waiterapp.class_models.ItemModel;
import se.miun.dt170g.waiterapp.class_models.OrderDTO;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ItemModel> itemModels;

    private final ItemsInterface itemsInterface;

    public ItemsAdapter(Context context, ArrayList<ItemModel> itemModels, ItemsInterface itemsInterface){
        this.context = context;
        this.itemModels = itemModels;
        this.itemsInterface = itemsInterface;
    }

    @NonNull
    @Override
    public ItemsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_template, parent, false);

        return new ItemsAdapter.MyViewHolder(view, itemsInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.MyViewHolder holder, int position) {

        holder.itemName.setText(itemModels.get(position).getItemName());
        holder.price.setText("Pris: " + itemModels.get(position).getPrice());
    }

    @Override
    public int getItemCount() { return itemModels.size(); }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView itemName, price;
        private Button deleteBtn;

        public MyViewHolder(@NonNull View itemView, ItemsInterface itemsInterface) {
            super(itemView);

            itemName = itemView.findViewById(R.id.FoodName_Item);
            price = itemView.findViewById(R.id.FoodPrice_Item);
            deleteBtn = itemView.findViewById(R.id.FoodDeleteBtn);

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemsInterface != null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            itemsInterface.deleteOnClickItem(position);
                        }
                    }
                }
            });
        }
    }
}

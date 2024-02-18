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
import se.miun.dt170g.waiterapp.class_models.DrinkModel;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<DrinkModel> drinks;

    public DrinkAdapter(Context context, ArrayList<DrinkModel> drinks){
        this.context = context;
        this.drinks = drinks;
    }

    @NonNull
    @Override
    public DrinkAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.input_template, parent, false);
        return new DrinkAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkAdapter.MyViewHolder holder, int position) {

        holder.name.setText(drinks.get(position).getName());
        holder.price.setText("Pris: " + drinks.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.FoodName_input);
            price = itemView.findViewById(R.id.FoodPrice_Item);
            //description = itemView.findViewById(R.id.drinkDescription);
        }
    }
}

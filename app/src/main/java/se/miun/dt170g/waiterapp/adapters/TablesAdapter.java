package se.miun.dt170g.waiterapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import se.miun.dt170g.waiterapp.MainActivity;
import se.miun.dt170g.waiterapp.OrderDetails;
import se.miun.dt170g.waiterapp.R;
import se.miun.dt170g.waiterapp.class_models.OrderDTO;
import se.miun.dt170g.waiterapp.class_models.TableModel;
import se.miun.dt170g.waiterapp.fetch.FetchData;
import se.miun.dt170g.waiterapp.fetch.Retro;

public class TablesAdapter extends RecyclerView.Adapter<TablesAdapter.MyViewHolder> {

    private final TablesInterface tablesInterface;

    private Context context;
    private ArrayList<TableModel> tableModels;
    private ArrayList<OrderDTO> activeOrders;

    private Retro retrofit = new Retro();
    private FetchData fetchData = retrofit.getRetrofit().create(FetchData.class);

    public TablesAdapter(Context context, ArrayList<TableModel> tableModels, ArrayList<OrderDTO> activeOrders, TablesInterface tablesInterface){
        this.context = context;
        this.tableModels = tableModels;
        this.tablesInterface = tablesInterface;
        this.activeOrders = activeOrders;
    }

    @NonNull
    @Override
    public TablesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.table_template, parent, false);

        return new TablesAdapter.MyViewHolder(view, tablesInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull TablesAdapter.MyViewHolder holder, int position) {

        holder.tableNumber.setText("Bord Nr: " + tableModels.get(position).getTableNumber());

        /*holder.appetizerStatus.setText("Förrätt: " + tableModels.get(position).get());
        holder.tableNumber.setText("Bord Nr: " + tableModels.get(position).getTableNumber());
        holder.tableNumber.setText("Bord Nr: " + tableModels.get(position).getTableNumber());*/

        if(tableModels.get(position).getTableStatus().equals("Free")){
            holder.tableCard.setCardBackgroundColor(ContextCompat.getColor(context.getApplicationContext(), R.color.green));

        }else if (tableModels.get(position).getTableStatus().equals("Active")){
            holder.tableCard.setCardBackgroundColor(ContextCompat.getColor(context.getApplicationContext(), R.color.orange));

            //Loop through the activeOrders to find the right order that belongs to the right table.
            for (int i = 0; i < activeOrders.size(); i++) {
                //check if the order belongs to the table.
                if(activeOrders.get(i).getRestaurantTableId() == tableModels.get(position).getTableNumber()){
                    //Check if the ststus is not none in the api
                    if(!activeOrders.get(i).getStatusAppetizer().equals("none"))
                        holder.appetizerStatus.setText("Förrätt: " + activeOrders.get(i).getStatusAppetizer());

                    if (!activeOrders.get(i).getStatusMain().equals("none"))
                        holder.mainStatus.setText("Huvudrätt: " + activeOrders.get(i).getStatusMain());

                    if(!activeOrders.get(i).getStatusDessert().equals("none"))
                        holder.dessertStatus.setText("Efterrätt: " + activeOrders.get(i).getStatusDessert());
                }
            }


        }else if (tableModels.get(position).getTableStatus().equals("Reserved")) {
            holder.tableCard.setCardBackgroundColor(ContextCompat.getColor(context.getApplicationContext(), R.color.red));

            //Loop through the activeOrders to find the right order that belongs to the right table.
            for (int i = 0; i < activeOrders.size(); i++) {
                //check if the order belongs to the table.
                if(activeOrders.get(i).getRestaurantTableId() == tableModels.get(position).getTableNumber()){
                    //Check if the ststus is not none in the api
                    if(!activeOrders.get(i).getStatusAppetizer().equals("none"))
                        holder.appetizerStatus.setText("Förrätt: " + activeOrders.get(i).getStatusAppetizer());

                    if (!activeOrders.get(i).getStatusMain().equals("none"))
                        holder.mainStatus.setText("Huvudrätt: " + activeOrders.get(i).getStatusMain());

                    if(!activeOrders.get(i).getStatusDessert().equals("none"))
                        holder.dessertStatus.setText("Efterrätt: " + activeOrders.get(i).getStatusDessert());
                }
            }
        }


    }

    @Override
    public int getItemCount() {
        return tableModels.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tableNumber, appetizerStatus, mainStatus, dessertStatus;
        CardView tableCard;

        public MyViewHolder(@NonNull View itemView, TablesInterface tablesInterface) {
            super(itemView);

            tableCard = itemView.findViewById(R.id.TableCard);
            tableNumber = itemView.findViewById(R.id.TableId);
            appetizerStatus = itemView.findViewById(R.id.AppetizerStatus);
            mainStatus = itemView.findViewById(R.id.MainStatus);
            dessertStatus = itemView.findViewById(R.id.DessertStatus);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(tablesInterface != null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            tablesInterface.onTableClick(position);
                        }
                    }
                }
            });
        }
    }

}

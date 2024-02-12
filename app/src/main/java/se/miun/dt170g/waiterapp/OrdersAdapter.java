package se.miun.dt170g.waiterapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {

    private final OrdersInterface ordersInterface;

    private Context context;
    private ArrayList<OrderModel> orderModels;

    public OrdersAdapter(Context context, ArrayList<OrderModel> orderModels, OrdersInterface ordersInterface){
        this.context = context;
        this.orderModels = orderModels;
        this.ordersInterface = ordersInterface;
    }

    @NonNull
    @Override
    public OrdersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_template, parent, false);

        return new OrdersAdapter.MyViewHolder(view, ordersInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.MyViewHolder holder, int position) {

        holder.tableId.setText("Bord Nr: " + String.valueOf(orderModels.get(position).getTableId()));
        holder.orderStatus.setText("Läge: " + orderModels.get(position).getStatus());

    }

    @Override
    public int getItemCount() {
        return orderModels.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tableId, orderStatus;

        public MyViewHolder(@NonNull View itemView, OrdersInterface ordersInterface) {
            super(itemView);

            tableId = itemView.findViewById(R.id.TableId);
            orderStatus = itemView.findViewById(R.id.OrderStatus);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ordersInterface != null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            ordersInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}

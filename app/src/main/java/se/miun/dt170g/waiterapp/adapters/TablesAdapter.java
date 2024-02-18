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
import se.miun.dt170g.waiterapp.class_models.TableModel;

public class TablesAdapter extends RecyclerView.Adapter<TablesAdapter.MyViewHolder> {

    private final TablesInterface tablesInterface;

    private Context context;
    private ArrayList<TableModel> tableModels;

    public TablesAdapter(Context context, ArrayList<TableModel> tableModels, TablesInterface tablesInterface){
        this.context = context;
        this.tableModels = tableModels;
        this.tablesInterface = tablesInterface;
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

        holder.tableStatus.setText("Bordsläge: " + tableModels.get(position).getTableStatus());

    }

    @Override
    public int getItemCount() {
        return tableModels.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tableNumber, tableStatus;

        public MyViewHolder(@NonNull View itemView, TablesInterface tablesInterface) {
            super(itemView);

            tableNumber = itemView.findViewById(R.id.TableId);
            tableStatus = itemView.findViewById(R.id.TableStatus);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(tablesInterface != null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            tablesInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}

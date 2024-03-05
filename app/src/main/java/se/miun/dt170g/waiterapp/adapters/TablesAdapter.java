package se.miun.dt170g.waiterapp.adapters;

import static androidx.core.content.ContextCompat.getSystemService;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
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




        if(tableModels.get(position).getTableStatus().equals("Free")){
            holder.tableCard.setCardBackgroundColor(ContextCompat.getColor(context.getApplicationContext(), R.color.green));

        }else if (tableModels.get(position).getTableStatus().equals("Active")){

            holder.tableCard.setCardBackgroundColor(ContextCompat.getColor(context.getApplicationContext(), R.color.orange));

            //Loop through the activeOrders to find the right order that belongs to the right table.
            for (int i = 0; i < activeOrders.size(); i++) {
                //check if the order belongs to the table.
                if(activeOrders.get(i).getRestaurantTableId() == tableModels.get(position).getTableNumber()){
                    //Check if the ststus is not none in the api
                    if(!activeOrders.get(i).getStatusAppetizer().equals("None"))
                        holder.appetizerStatus.setText("Förrätt: " + activeOrders.get(i).getStatusAppetizer());

                    if(activeOrders.get(i).getStatusAppetizer().equals("COMPLETED"))
                        showNotification(activeOrders.get(i).getRestaurantTableId(), "Förrätt färdig");



                    if (!activeOrders.get(i).getStatusMain().equals("None"))
                        holder.mainStatus.setText("Huvudrätt: " + activeOrders.get(i).getStatusMain());

                    if(activeOrders.get(i).getStatusMain().equals("COMPLETED"))
                        showNotification(activeOrders.get(i).getRestaurantTableId(), "Huvudrätt färdig");



                    if(!activeOrders.get(i).getStatusDessert().equals("None"))
                        holder.dessertStatus.setText("Efterrätt: " + activeOrders.get(i).getStatusDessert());

                    if(activeOrders.get(i).getStatusDessert().equals("COMPLETED"))
                        showNotification(activeOrders.get(i).getRestaurantTableId(), "Efterrätt färdig");
                }
            }

        }else if(tableModels.get(position).getTableStatus().equals("Reserved")){
            holder.tableCard.setCardBackgroundColor(ContextCompat.getColor(context.getApplicationContext(), R.color.red));
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



    private static int notificationId = 0;

    private void createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Notification Channel";
            String description = "Channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
            channel.setDescription(description);
            // Aktivera Ljud och Vibration
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{0, 1000, 500, 1000}); // Vibration pattern
            channel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), null);

            // Registrera kanalen med systemet
            //NotificationManager notificationManager = getSystemService(NotificationManager.class);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }



    @SuppressLint("MissingPermission")
    private void showNotification(int tableId, String msg) {

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context.getApplicationContext()); //this
        createNotification();


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), "CHANNEL_ID")
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setContentTitle("Table Nr: " + tableId)
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setVibrate(new long[]{0, 1000, 500, 1000}) // Egna vibrationsmönster: vänta, vibrera, vänta, vibrera
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)); // Standard ljud



        notificationManager.notify(++notificationId, builder.build()); // notificationId är ett unikt int för varje notifikation
        Log.d("Noti", "showNotification" + notificationId);



       /* for (OrderDTO order : activeOrders) {
            if (order.getStatusAppetizer().equals("c")) {

                // Skapa och visa notifikation //this
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), "CHANNEL_ID")
                        .setContentTitle("förrätt")
                        .setContentText("En förrätt behöver din uppmärksamhet")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setVibrate(new long[]{0, 1000, 500, 1000}) // Egna vibrationsmönster: vänta, vibrera, vänta, vibrera
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)); // Standard ljud

                notificationManager.notify(++notificationId, builder.build()); // notificationId är ett unikt int för varje notifikation
                Log.d("Noti", "showNotification" + notificationId);
                break; // Avbryt loopen efter att första matchande notifikationen visas
            }
        }*/

    }

}

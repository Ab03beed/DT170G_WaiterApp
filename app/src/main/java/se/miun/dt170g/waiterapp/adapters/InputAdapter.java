package se.miun.dt170g.waiterapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import se.miun.dt170g.waiterapp.R;
import se.miun.dt170g.waiterapp.class_models.InputModel;

public class InputAdapter extends RecyclerView.Adapter<InputAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<InputModel> inputModels;

    private boolean isChanged = false;

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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull InputAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.itemName.setText(inputModels.get(position).getItemName());
        holder.price.setText("Pris: " + String.valueOf(inputModels.get(position).getPrice()));


        holder.inputCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Toast.makeText(context.getApplicationContext(), "bEFORE", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isChanged){
                    if (!s.toString().equals("")){
                        inputModels.get(position).setCount(Integer.parseInt(s.toString()));
                    }
                    isChanged = false; //Switch back isChanged to false.
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return inputModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView itemName, price;
        private EditText inputCount;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.FoodName_input);
            price = itemView.findViewById(R.id.FoodPrice_Item);
            inputCount = itemView.findViewById(R.id.FoodCount);


        }
    }

}



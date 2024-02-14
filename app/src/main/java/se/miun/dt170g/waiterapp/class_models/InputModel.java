package se.miun.dt170g.waiterapp.class_models;

import android.widget.NumberPicker;

public class InputModel {

    private String itemName;
    private int price;
   // private NumberPicker numberPicker;
    private int itemCount;

    public InputModel(String itemName, int price, int itemCount) {
        this.itemName = itemName;
        this.price = price;
        this.itemCount = itemCount;
        //this.numberPicker = numberPicker;
    }

    public String getItemName() {
        return itemName;
    }

    public int getPrice() {
        return price;
    }

    public int getItemCount() {
        return itemCount;
    }

   /* public NumberPicker getNumberPicker() {
        return numberPicker;
    }*/

}

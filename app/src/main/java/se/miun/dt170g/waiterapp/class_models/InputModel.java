package se.miun.dt170g.waiterapp.class_models;

import android.widget.NumberPicker;

public class InputModel {
    private int id;
    private int price;
    private String itemName;
    private String type;
    private String description;
    private int count;

    public InputModel(int id, int price, String itemName, String type, String description) {
        this.id = id;
        this.price = price;
        this.itemName = itemName;
        this.type = type;
        this.description = description;
    }




    public String getItemName() {
        return itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

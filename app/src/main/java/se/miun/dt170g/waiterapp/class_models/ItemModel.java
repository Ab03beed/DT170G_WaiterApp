package se.miun.dt170g.waiterapp.class_models;

public class ItemModel {
    private String itemName;
    private int price;

    public ItemModel(String itemName, int price) {
        this.price = price;
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public int getPrice() {
        return price;
    }



}

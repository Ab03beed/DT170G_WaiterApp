package se.miun.dt170g.waiterapp.class_models;

public class ItemModel {
    private int itemId;
    private String itemName;
    private int price;

    public ItemModel(int itemId, String itemName, int price) {
        this.itemId =itemId;
        this.itemName = itemName;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return itemId;
    }





}

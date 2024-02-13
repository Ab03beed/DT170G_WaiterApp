package se.miun.dt170g.waiterapp.class_models;

public class InputModel {

    private String itemName;
    private int price;
    private int itemCount;

    public InputModel(String itemName, int price, int itemCount) {
        this.itemName = itemName;
        this.price = price;
        this.itemCount = itemCount;
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


}

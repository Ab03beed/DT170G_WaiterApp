package se.miun.dt170g.waiterapp.class_models;

import java.io.Serializable;

public class DrinkModel implements Serializable {
    private int drinkID;
    private String name;
    private String description;
    private int price;

    public DrinkModel(int drink_id, String name, String description, int price) {
        this.drinkID = drink_id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getDrink_id() {
        return drinkID;
    }

    public void setDrink_id(int drink_id) {
        this.drinkID = drink_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}




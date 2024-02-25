package se.miun.dt170g.waiterapp.class_models;


import java.util.*;

public class OrderDTO {

    private int order_ID;

    private String statusAppetizer;

    private String statusMain;

    private String statusDessert;

    private int restaurantTableId;

    private String comment;

    private ArrayList<ALaCarteModel> foods;

    private ArrayList<DrinkModel> drinks;

    public OrderDTO(int order_ID, String statusAppetizer, String statusMain, String statusDessert, int restaurantTableId, String comment, ArrayList<ALaCarteModel> foods, ArrayList<DrinkModel> drinks) {
        this.order_ID = order_ID;
        this.statusAppetizer = statusAppetizer;
        this.statusMain = statusMain;
        this.statusDessert = statusDessert;
        this.restaurantTableId = restaurantTableId;
        this.comment = comment;
        this.foods = foods;
        this.drinks = drinks;
    }

    public OrderDTO() {
        foods = new ArrayList<ALaCarteModel>();
        drinks = new ArrayList<DrinkModel>();
    }

    public int getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(int order_ID) {
        this.order_ID = order_ID;
    }

    public String getStatusAppetizer() {
        return statusAppetizer;
    }

    public void setStatusAppetizer(String statusAppetizer) {
        this.statusAppetizer = statusAppetizer;
    }

    public String getStatusMain() {
        return statusMain;
    }

    public void setStatusMain(String statusMain) {
        this.statusMain = statusMain;
    }

    public String getStatusDessert() {
        return statusDessert;
    }

    public void setStatusDessert(String statusDessert) {
        this.statusDessert = statusDessert;
    }

    public int getRestaurantTableId() {
        return restaurantTableId;
    }

    public void setRestaurantTableId(int restaurantTableId) {
        this.restaurantTableId = restaurantTableId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<ALaCarteModel> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<ALaCarteModel> foods) {
        this.foods = foods;
    }

    public List<DrinkModel> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<DrinkModel> drinks) {
        this.drinks = drinks;
    }
    public void addDrink(DrinkModel drink){
        drinks.add(drink);
    };
    public void addFood(ALaCarteModel food){
        foods.add(food);
    };

}
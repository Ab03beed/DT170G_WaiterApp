package se.miun.dt170g.waiterapp.class_models;

public class OrderModel {

    private int restaurantOrderId;
    private String status;
    private int restaurantTableId;
    private String comment;


    public OrderModel(int restaurantOrderId, String status, int restaurantTableId, String comment) {
        this.restaurantOrderId = restaurantOrderId;
        this.status = status;
        this.restaurantTableId = restaurantTableId;
        this.comment = comment;
    }

    public int getRestaurantOrderId() {
        return restaurantOrderId;
    }

    public void setRestaurantOrderId(int restaurantOrderId) {
        this.restaurantOrderId = restaurantOrderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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



}

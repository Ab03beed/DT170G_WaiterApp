package se.miun.dt170g.waiterapp.class_models;

public class PurchasedALaCarteEntity {


    private int purchasedId;
    private OrderModel restaurantOrderByOrderId;
    private ALaCarteModel aLaCarteMenuByALaCarteId;

    public OrderModel getRestaurantOrderByOrderId() {
        return restaurantOrderByOrderId;
    }

    public void setRestaurantOrderByOrderId(OrderModel restaurantOrderByOrderId) {
        this.restaurantOrderByOrderId = restaurantOrderByOrderId;
    }

    public ALaCarteModel getaLaCarteMenuByALaCarteId() {
        return aLaCarteMenuByALaCarteId;
    }

    public void setaLaCarteMenuByALaCarteId(ALaCarteModel aLaCarteMenuByALaCarteId) {
        this.aLaCarteMenuByALaCarteId = aLaCarteMenuByALaCarteId;
    }


    public int getPurchasedId() {
        return purchasedId;
    }

    public void setPurchasedId(int purchasedId) {
        this.purchasedId = purchasedId;
    }

    public int getOrderId() {
        return restaurantOrderByOrderId.getRestaurantOrderId();
    }

    public int getaLaCarteId() {
        return aLaCarteMenuByALaCarteId.getaLaCarteID();
    }

}

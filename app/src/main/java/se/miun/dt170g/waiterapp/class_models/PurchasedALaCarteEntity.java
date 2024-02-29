package se.miun.dt170g.waiterapp.class_models;

public class PurchasedALaCarteEntity {


    private int purchasedId;
    private OrderModel restaurantOrderByOrderId;
    private ALaCarteItem aLaCarteMenuByALaCarteId;

    public OrderModel getRestaurantOrderByOrderId() {
        return restaurantOrderByOrderId;
    }

    public void setRestaurantOrderByOrderId(OrderModel restaurantOrderByOrderId) {
        this.restaurantOrderByOrderId = restaurantOrderByOrderId;
    }

    public ALaCarteItem getaLaCarteMenuByALaCarteId() {
        return aLaCarteMenuByALaCarteId;
    }

    public void setaLaCarteMenuByALaCarteId(ALaCarteItem aLaCarteMenuByALaCarteId) {
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

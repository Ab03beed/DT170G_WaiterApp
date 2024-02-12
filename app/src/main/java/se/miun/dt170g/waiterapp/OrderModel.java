package se.miun.dt170g.waiterapp;

public class OrderModel {

     int id;
     int tableId;
     String status;
     String comment;


    public OrderModel(int id, int tableId, String status, String comment) {
        this.id = id;
        this.status = status;
        this.tableId = tableId;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public int getTableId() {
        return tableId;
    }

    public String getComment() {
        return comment;
    }


}

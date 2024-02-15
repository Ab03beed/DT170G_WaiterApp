package se.miun.dt170g.waiterapp.class_models;

public class TableModel {

     int tableId;
     int status;
     String comment;


    public TableModel(int tableId, int status, String comment) {
        this.status = status;
        this.tableId = tableId;
        this.comment = comment;
    }


    public int getStatus() {
        return status;
    }

    public int getTableId() {
        return tableId;
    }

    public String getComment() {
        return comment;
    }


}

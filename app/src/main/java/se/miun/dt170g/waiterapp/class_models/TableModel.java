package se.miun.dt170g.waiterapp.class_models;

public class TableModel {

    private int sessionId;

    private int tableNumber;

    private int tableSize;

    private String status;


    public TableModel(int sessionId, int tableNumber, int tableSize, String status) {
        this.sessionId = sessionId;
        this.tableNumber = tableNumber;
        this.tableSize = tableSize;
        this.status = status;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getTableSize() {
        return tableSize;
    }

    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }

    public String getTableStatus() {
        return status;
    }

    public void setTableStatus(String status) {
        this.status = status;
    }



}

package se.miun.dt170g.waiterapp.class_models;

public class TableModel {

    private int sessionId;

    private int tableNumber;

    private int tableSize;

    private int tableStatus;


    public TableModel(int sessionId, int tableNumber, int tableSize, int tableStatus) {
        this.sessionId = sessionId;
        this.tableNumber = tableNumber;
        this.tableSize = tableSize;
        this.tableStatus = tableStatus;
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

    public int getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(int tableStatus) {
        this.tableStatus = tableStatus;
    }



}

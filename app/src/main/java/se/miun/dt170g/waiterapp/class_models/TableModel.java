package se.miun.dt170g.waiterapp.class_models;

import java.io.Serializable;

public class TableModel implements Serializable {


    private int tableNumber;

    private int tableSize;

    private String status;


    public TableModel(int tableNumber, int tableSize, String status) {

        this.tableNumber = tableNumber;
        this.tableSize = tableSize;
        this.status = status;
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

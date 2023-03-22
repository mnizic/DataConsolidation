package com.github.mnizic.dataconsolidation.model;

public class StoreLocation {
    private String ID;
    private String name;

    public StoreLocation() {

    }

    public StoreLocation(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

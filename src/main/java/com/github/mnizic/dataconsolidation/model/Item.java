package com.github.mnizic.dataconsolidation.model;

public class Item {
    private String ID;
    private String name;
    private MeasurementUnit unit;

    public Item() {

    }

    public Item(String ID, String name, MeasurementUnit unit) {
        this.ID = ID;
        this.name = name;
        this.unit = unit;
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

    public MeasurementUnit getUnit() {
        return unit;
    }

    public void setUnit(MeasurementUnit unit) {
        this.unit = unit;
    }
}

package com.github.mnizic.dataconsolidation.model;

public class InventoryItem {
    private String itemID;
    private String storeLocationID;
    private double quantity;

    public InventoryItem() {

    }

    public InventoryItem(String itemID, String storeLocationID, double quantity) {
        this.itemID = itemID;
        this.storeLocationID = storeLocationID;
        this.quantity = quantity;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getStoreLocationID() {
        return storeLocationID;
    }

    public void setStoreLocationID(String storeLocationID) {
        this.storeLocationID = storeLocationID;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}

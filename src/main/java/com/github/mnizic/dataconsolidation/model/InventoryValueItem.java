package com.github.mnizic.dataconsolidation.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class InventoryValueItem {
    private String itemID;
    private String itemName;
    private double itemPrice;
    private double itemQuantity;
    private MeasurementUnit itemUnit;
    private double itemTotalAmountEUR;
    private double itemTotalAmountForeignCurr;
    private long storeLocationCount;

    public InventoryValueItem() {

    }
    public InventoryValueItem(String itemID, String itemName, double itemPrice, double itemQuantity, MeasurementUnit itemUnit, double itemTotalAmountEUR, double itemTotalAmountForeignCurr, long storeLocationCount) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.itemUnit = itemUnit;
        this.itemTotalAmountEUR = itemTotalAmountEUR;
        this.itemTotalAmountForeignCurr = itemTotalAmountForeignCurr;
        this.storeLocationCount = storeLocationCount;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(double itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public MeasurementUnit getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(MeasurementUnit itemUnit) {
        this.itemUnit = itemUnit;
    }

    public double getItemTotalAmountEUR() {
        return itemTotalAmountEUR;
    }

    public void setItemTotalAmountEUR(double itemTotalAmountEUR) {
        this.itemTotalAmountEUR = itemTotalAmountEUR;
    }

    public double getItemTotalAmountForeignCurr() {
        return itemTotalAmountForeignCurr;
    }

    public void setItemTotalAmountForeignCurr(double itemTotalAmountForeignCurr) {
        this.itemTotalAmountForeignCurr = itemTotalAmountForeignCurr;
    }

    public long getStoreLocationCount() {
        return storeLocationCount;
    }

    public void setStoreLocationCount(long storeLocationCount) {
        this.storeLocationCount = storeLocationCount;
    }

    @Override
    public String toString() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("#,##0.00", symbols);

        return itemID + "\t" + itemName + "\t" + df.format(itemPrice) + "\t" + df.format(itemQuantity) + "\t" + itemUnit + "\t" + df.format(itemTotalAmountEUR)
                + "\t" + df.format(itemTotalAmountForeignCurr) + "\t" + storeLocationCount;
    }
}

package com.github.mnizic.dataconsolidation.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class InventoryValueSL {
    private String storeLocationID;
    private String storeLocationName;
    private double storeLocationTotalAmountEUR;
    private double getStoreLocationTotalAmountForeignCurr;
    private long itemCount;

    public InventoryValueSL() {
    }

    public InventoryValueSL(String storeLocationID, String storeLocationName, double storeLocationTotalAmountEUR, double getStoreLocationTotalAmountForeignCurr, long itemCount) {
        this.storeLocationID = storeLocationID;
        this.storeLocationName = storeLocationName;
        this.storeLocationTotalAmountEUR = storeLocationTotalAmountEUR;
        this.getStoreLocationTotalAmountForeignCurr = getStoreLocationTotalAmountForeignCurr;
        this.itemCount = itemCount;
    }

    public String getStoreLocationID() {
        return storeLocationID;
    }

    public void setStoreLocationID(String storeLocationID) {
        this.storeLocationID = storeLocationID;
    }

    public String getStoreLocationName() {
        return storeLocationName;
    }

    public void setStoreLocationName(String storeLocationName) {
        this.storeLocationName = storeLocationName;
    }

    public double getStoreLocationTotalAmountEUR() {
        return storeLocationTotalAmountEUR;
    }

    public void setStoreLocationTotalAmountEUR(double storeLocationTotalAmountEUR) {
        this.storeLocationTotalAmountEUR = storeLocationTotalAmountEUR;
    }

    public double getGetStoreLocationTotalAmountForeignCurr() {
        return getStoreLocationTotalAmountForeignCurr;
    }

    public void setGetStoreLocationTotalAmountForeignCurr(double getStoreLocationTotalAmountForeignCurr) {
        this.getStoreLocationTotalAmountForeignCurr = getStoreLocationTotalAmountForeignCurr;
    }

    public long getItemCount() {
        return itemCount;
    }

    public void setItemCount(long itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public String toString() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("#,##0.00", symbols);

        return storeLocationID + "\t" + storeLocationName + "\t"
                + df.format(storeLocationTotalAmountEUR) + "\t"
                + df.format(getStoreLocationTotalAmountForeignCurr)
                + "\t" + itemCount;
    }
}

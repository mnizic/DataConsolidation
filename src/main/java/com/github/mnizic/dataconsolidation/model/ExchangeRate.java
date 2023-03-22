package com.github.mnizic.dataconsolidation.model;

public class ExchangeRate {
    private String ID;
    private String code;
    private double buyingExchangeRate;
    private double averageExchangeRate;
    private double sellingExchangeRate;

    public ExchangeRate() {

    }

    public ExchangeRate(String ID, String code, double buyingExchangeRate, double averageExchangeRate, double sellingExchangeRate) {
        this.ID = ID;
        this.code = code;
        this.buyingExchangeRate = buyingExchangeRate;
        this.averageExchangeRate = averageExchangeRate;
        this.sellingExchangeRate = sellingExchangeRate;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getBuyingExchangeRate() {
        return buyingExchangeRate;
    }

    public void setBuyingExchangeRate(double buyingExchangeRate) {
        this.buyingExchangeRate = buyingExchangeRate;
    }

    public double getAverageExchangeRate() {
        return averageExchangeRate;
    }

    public void setAverageExchangeRate(double averageExchangeRate) {
        this.averageExchangeRate = averageExchangeRate;
    }

    public double getSellingExchangeRate() {
        return sellingExchangeRate;
    }

    public void setSellingExchangeRate(double sellingExchangeRate) {
        this.sellingExchangeRate = sellingExchangeRate;
    }
}

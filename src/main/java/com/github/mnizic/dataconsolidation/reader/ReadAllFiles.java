package com.github.mnizic.dataconsolidation.reader;

public class ReadAllFiles {
    public static void readAll() {
        Reader frExchangeRate = new ExchangeRateReader();
        Reader frItems = new ItemsReader();
        Reader frPriceListItems = new PriceListItemReader();
        Reader frStoreLocations = new StoreLocationReader();
        Reader frInventory = new InventoryReader();
        try {
            frExchangeRate.getData("tecaj.txt");
            frItems.getData("artikli.txt");
            frPriceListItems.getData("cjenik.txt");
            frStoreLocations.getData("pm.txt");
            frInventory.getData("stanja.txt");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

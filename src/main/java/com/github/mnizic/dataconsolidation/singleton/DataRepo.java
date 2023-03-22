package com.github.mnizic.dataconsolidation.singleton;

import com.github.mnizic.dataconsolidation.model.*;

import java.util.ArrayList;
import java.util.List;

public class DataRepo {
    private static DataRepo instance;

    private DataRepo() {

    }

    public static DataRepo getInstance() {
        if (instance == null) {
            instance = new DataRepo();
        }
        return instance;
    }

    public List<Item> itemList = new ArrayList<>();
    public List<PriceListItem> priceList = new ArrayList<>();
    public List<StoreLocation> storeLocationList = new ArrayList<>();
    public List<InventoryItem> inventoryItemList = new ArrayList<>();
    public List<ExchangeRate> exchangeRateList = new ArrayList<>();
    public List<InventoryValueItem> inventoryValueItemList = new ArrayList<>();
    public List<InventoryValueSL> inventoryValueSLList = new ArrayList<>();
}

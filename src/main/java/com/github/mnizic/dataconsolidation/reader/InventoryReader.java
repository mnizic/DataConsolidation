package com.github.mnizic.dataconsolidation.reader;

import com.github.mnizic.dataconsolidation.model.InventoryItem;
import com.github.mnizic.dataconsolidation.singleton.DataRepo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class InventoryReader implements Reader {
    @Override
    public void getData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String row = "";
            while ((row = br.readLine()) != null) {
                String[] splitRow = row.split("\\|");
                if (splitRow.length != 3) continue;
                InventoryItem newInventoryItem = createAndValidateNewInventoryItem(splitRow);
                DataRepo.getInstance().inventoryItemList.add(newInventoryItem);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private InventoryItem createAndValidateNewInventoryItem(String[] splitRow) {
        InventoryItem newInventoryItem = new InventoryItem();
        try {
            String itemID = validateID(splitRow[0]);
            String storeLocationID = validateID(splitRow[1]);
            double quantity = validateQuantity(splitRow[2]);
            if(quantity != -1) {
                newInventoryItem.setItemID(itemID);
                newInventoryItem.setStoreLocationID(storeLocationID);
                newInventoryItem.setQuantity(quantity);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return newInventoryItem;
    }

    private double validateQuantity(String stringQuantity) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat format = new DecimalFormat("#,##0.##", symbols);
        try {
            Number decimalNumber = format.parse(stringQuantity);
            return decimalNumber.doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private String validateID(String stringID) throws Exception {
        if (!stringID.matches("\\d+")) throw new Exception("ID is not numerical.");
        return stringID;
    }
}

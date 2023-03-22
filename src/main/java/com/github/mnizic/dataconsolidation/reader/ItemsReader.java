package com.github.mnizic.dataconsolidation.reader;

import com.github.mnizic.dataconsolidation.model.Item;
import com.github.mnizic.dataconsolidation.model.MeasurementUnit;
import com.github.mnizic.dataconsolidation.singleton.DataRepo;

import java.io.BufferedReader;
import java.io.FileReader;

public class ItemsReader implements Reader {

    @Override
    public void getData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String row = "";
            while ((row = br.readLine()) != null) {
                String[] splitRow = row.split("\\|");
                if (splitRow.length != 3) continue;
                Item newItem = createAndValidateNewItem(splitRow);
                if (!isDuplicate(newItem)) {
                    DataRepo.getInstance().itemList.add(newItem);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean isDuplicate(Item newItem) {
        return DataRepo.getInstance().itemList.stream().anyMatch(x -> x.getID().equals(newItem.getID()));
    }

    private Item createAndValidateNewItem(String[] splitRow) {
        Item newItem = new Item();
        try {
            String ID = validateID(splitRow[0]);
            String name = splitRow[1];
            MeasurementUnit unit = validateMU(splitRow[2]);
            newItem.setID(ID);
            newItem.setName(name);
            newItem.setUnit(unit);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return newItem;
    }

    private MeasurementUnit validateMU(String stringUnit) throws Exception {
        switch (stringUnit) {
            case "KG":
                return MeasurementUnit.KG;
            case "M":
                return MeasurementUnit.M;
            case "KOM":
                return MeasurementUnit.KOM;
            case "LIT":
                return MeasurementUnit.LIT;
            default:
                throw new Exception("Invalid measurement unit.");
        }
    }

    private String validateID(String stringID) throws Exception {
        if (!stringID.matches("\\d+")) throw new Exception("ID is not numerical.");
        return stringID;
    }
}

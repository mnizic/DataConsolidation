package com.github.mnizic.dataconsolidation.reader;

import com.github.mnizic.dataconsolidation.model.StoreLocation;
import com.github.mnizic.dataconsolidation.singleton.DataRepo;

import java.io.BufferedReader;
import java.io.FileReader;

public class StoreLocationReader implements Reader {
    @Override
    public void getData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String row = "";
            while ((row = br.readLine()) != null) {
                String[] splitRow = row.split("\\|");
                if (splitRow.length != 2) continue;
                StoreLocation newStoreLocation = createAndValidateNewStoreLocation(splitRow);
                if (!isDuplicate(newStoreLocation)) {
                    DataRepo.getInstance().storeLocationList.add(newStoreLocation);
                } else {
                    System.out.println(newStoreLocation.getID() + " is a duplicate.");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean isDuplicate(StoreLocation newStoreLocation) {
        return DataRepo.getInstance().storeLocationList.stream().anyMatch(x -> x.getID().equals(newStoreLocation.getID()));
    }

    private StoreLocation createAndValidateNewStoreLocation(String[] splitRow) {
        StoreLocation newStoreLocation = new StoreLocation();
        try {
            String ID = validateID(splitRow[0]);
            String name = splitRow[1];
            newStoreLocation.setID(ID);
            newStoreLocation.setName(name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return newStoreLocation;
    }

    private String validateID(String stringID) throws Exception {
        if (!stringID.matches("\\d+")) throw new Exception("ID is not numerical.");
        return stringID;
    }
}

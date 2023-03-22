package com.github.mnizic.dataconsolidation.writer;

import com.github.mnizic.dataconsolidation.model.ExchangeRate;
import com.github.mnizic.dataconsolidation.model.InventoryValueSL;
import com.github.mnizic.dataconsolidation.model.StoreLocation;
import com.github.mnizic.dataconsolidation.singleton.DataRepo;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Comparator;

public class InventoryValueSLWriter implements Writer {
    @Override
    public void WriteData(String fileName, ExchangeRate exchangeRate) {
        generateData(exchangeRate);
        sortData();
        for (InventoryValueSL inventoryValueSL : DataRepo.getInstance().inventoryValueSLList) {
            System.out.println(inventoryValueSL.toString());
        }
        writeToFile(fileName);
    }

    private void writeToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), "UTF-8"))) {
            for (InventoryValueSL inventoryValueSL : DataRepo.getInstance().inventoryValueSLList) {
                writer.write(inventoryValueSL.toString());
                writer.write(System.lineSeparator());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void sortData() {
        DataRepo.getInstance().inventoryValueSLList.sort(Comparator.comparing(InventoryValueSL::getStoreLocationID));
    }

    private void generateData(ExchangeRate exchangeRate) {
        for (StoreLocation storeLocation : DataRepo.getInstance().storeLocationList) {
            InventoryValueSL newInventoryValueSL = new InventoryValueSL();
            newInventoryValueSL.setStoreLocationID(storeLocation.getID());
            newInventoryValueSL.setStoreLocationName(storeLocation.getName());

            double totalAmountEur = DataRepo.getInstance().inventoryItemList
                    .stream()
                    .filter(x -> x.getStoreLocationID().equals(storeLocation.getID()))
                    .mapToDouble(y -> DataRepo.getInstance().priceList
                            .stream()
                            .filter(z -> z.getItemID().equals(y.getItemID()))
                            .findFirst()
                            .get()
                            .getPrice() * y.getQuantity())
                    .sum();

            double totalAmountForeignCurr = totalAmountEur * exchangeRate.getAverageExchangeRate();
            long itemCount = DataRepo.getInstance().inventoryItemList
                    .stream()
                    .filter(x -> x.getStoreLocationID().equals(storeLocation.getID()))
                    .count();

            newInventoryValueSL.setStoreLocationTotalAmountEUR(totalAmountEur);
            newInventoryValueSL.setGetStoreLocationTotalAmountForeignCurr(totalAmountForeignCurr);
            newInventoryValueSL.setItemCount(itemCount);
            DataRepo.getInstance().inventoryValueSLList.add(newInventoryValueSL);
        }
    }
}

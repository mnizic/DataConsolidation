package com.github.mnizic.dataconsolidation.writer;

import com.github.mnizic.dataconsolidation.model.ExchangeRate;
import com.github.mnizic.dataconsolidation.model.InventoryValueItem;
import com.github.mnizic.dataconsolidation.model.Item;
import com.github.mnizic.dataconsolidation.singleton.DataRepo;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Comparator;

public class InventoryValueItemsWriter implements Writer {
    @Override
    public void WriteData(String fileName, ExchangeRate exchangeRate) {
        generateData(exchangeRate);
        sortData();
        writeToFile(fileName);
    }

    private void writeToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), "UTF-8"))) {
            for(InventoryValueItem inventoryValueItem : DataRepo.getInstance().inventoryValueItemList){
                writer.write(inventoryValueItem.toString());
                writer.write(System.lineSeparator());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void sortData() {
        DataRepo.getInstance().inventoryValueItemList.sort(Comparator.comparing(InventoryValueItem::getItemID));
    }

    private void generateData(ExchangeRate exchangeRate) {
        for (Item item : DataRepo.getInstance().itemList) {
            InventoryValueItem newInventoryValueItem = new InventoryValueItem();
            double price = 0;

            if(DataRepo.getInstance().priceList
                    .stream()
                    .filter(x -> x.getItemID().equals(item.getID()))
                    .findFirst().isPresent()) {
                price = DataRepo.getInstance().priceList
                        .stream()
                        .filter(x -> x.getItemID().equals(item.getID()))
                        .findFirst().get().getPrice();
            }

            double quantity = 0;

            if(DataRepo.getInstance().inventoryItemList
                    .stream()
                    .filter(x -> x.getItemID().equals(item.getID()))
                    .findFirst().isPresent()) {
                quantity = DataRepo.getInstance().inventoryItemList
                        .stream()
                        .filter(x -> x.getItemID().equals(item.getID()))
                        .findFirst().get().getQuantity();
            }

            long numberOfShopsContainingItem = DataRepo.getInstance().inventoryItemList
                    .stream()
                    .filter(x -> x.getItemID().equals(item.getID()))
                    .count();

            newInventoryValueItem.setItemID(item.getID());
            newInventoryValueItem.setItemName(item.getName());
            newInventoryValueItem.setItemPrice(price);
            newInventoryValueItem.setItemQuantity(quantity);
            newInventoryValueItem.setItemUnit(item.getUnit());
            newInventoryValueItem.setItemTotalAmountEUR(price * quantity);
            newInventoryValueItem.setItemTotalAmountForeignCurr(exchangeRate.getAverageExchangeRate() * price * quantity);
            newInventoryValueItem.setStoreLocationCount(numberOfShopsContainingItem);
            DataRepo.getInstance().inventoryValueItemList.add(newInventoryValueItem);
        }
    }
}

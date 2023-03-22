package com.github.mnizic.dataconsolidation.writer;

import com.github.mnizic.dataconsolidation.model.ExchangeRate;

public class WriteAllFiles {
    public static void writeAll(ExchangeRate exchangeRate) {
        Writer fwInventoryValueItems = new InventoryValueItemsWriter();
        Writer fwInventoryValueSLs = new InventoryValueSLWriter();

        fwInventoryValueItems.WriteData("vrijednost zalihe - artikli.txt", exchangeRate);
        fwInventoryValueSLs.WriteData("vrijednost zalihe - PM.txt", exchangeRate);
    }
}

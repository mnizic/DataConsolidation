package com.github.mnizic.dataconsolidation.reader;

import com.github.mnizic.dataconsolidation.model.PriceListItem;
import com.github.mnizic.dataconsolidation.singleton.DataRepo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class PriceListItemReader implements Reader {
    @Override
    public void getData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String row = "";
            while ((row = br.readLine()) != null) {
                String[] splitRow = row.split("\\|");
                if (splitRow.length != 2) continue;
                PriceListItem newPriceListItem = createAndValidateNewPLItem(splitRow);
                DataRepo.getInstance().priceList.add(newPriceListItem);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private PriceListItem createAndValidateNewPLItem(String[] splitRow) {
        PriceListItem priceListItem = new PriceListItem();
        try {
            String ID = validateID(splitRow[0]);
            double price = validatePrice(splitRow[1]);
            if (price != -1) {
                priceListItem.setItemID(ID);
                priceListItem.setPrice(price);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return priceListItem;
    }

    private double validatePrice(String stringPrice) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat format = new DecimalFormat("#,##0.##", symbols);
        try {
            Number decimalNumber = format.parse(stringPrice);
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

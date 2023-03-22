package com.github.mnizic.dataconsolidation.reader;

import com.github.mnizic.dataconsolidation.model.ExchangeRate;
import com.github.mnizic.dataconsolidation.singleton.DataRepo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class ExchangeRateReader implements Reader {
    @Override
    public void getData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String row = "";
            while ((row = br.readLine()) != null) {
                String[] splitRow = row.split("\\|");
                if (splitRow.length != 5) continue;
                ExchangeRate newExchangeRate = createAndValidateNewERItem(splitRow);
                DataRepo.getInstance().exchangeRateList.add(newExchangeRate);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private ExchangeRate createAndValidateNewERItem(String[] splitRow) {
        ExchangeRate newExchangeRate = new ExchangeRate();
        try {
            String ID = validateID(splitRow[0]);
            String code = validateCode(splitRow[1]);
            double buyingER = validateExchangeRate(splitRow[2]);
            double averageER = validateExchangeRate(splitRow[3]);
            double sellingER = validateExchangeRate(splitRow[4]);

            if (buyingER != -1 && averageER != -1 && sellingER != -1) {
                newExchangeRate.setID(ID);
                newExchangeRate.setCode(code);
                newExchangeRate.setBuyingExchangeRate(buyingER);
                newExchangeRate.setAverageExchangeRate(averageER);
                newExchangeRate.setSellingExchangeRate(sellingER);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return newExchangeRate;
    }

    private double validateExchangeRate(String stringExchangeRate) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat format = new DecimalFormat("#,##0.##", symbols);
        try {
            Number decimalNumber = format.parse(stringExchangeRate);
            return decimalNumber.doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private String validateCode(String stringCode) throws Exception {
        if (!stringCode.matches("[A-Z]{3}")) throw new Exception("Currency code should be 3 uppercase letters.");
        return stringCode;
    }

    private String validateID(String stringID) throws Exception {
        if (!stringID.matches("\\d+")) throw new Exception("ID is not numerical.");
        return stringID;
    }
}

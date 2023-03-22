package com.github.mnizic.dataconsolidation;

import com.github.mnizic.dataconsolidation.model.ExchangeRate;
import com.github.mnizic.dataconsolidation.reader.ReadAllFiles;
import com.github.mnizic.dataconsolidation.singleton.DataRepo;
import com.github.mnizic.dataconsolidation.writer.WriteAllFiles;

public class Main {
    public static void main(String[] args) {
        try {
            ReadAllFiles.readAll();
            checkArgs(args);
            ExchangeRate exchangeRate = setExchangeRate(args);
            WriteAllFiles.writeAll(exchangeRate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static ExchangeRate setExchangeRate(String[] args) {
        return DataRepo.getInstance().exchangeRateList
                .stream()
                .filter(x -> x.getCode().equals(args[0]))
                .findFirst().get();
    }

    private static void checkArgs(String[] args) throws Exception {
        if (args.length != 1) throw new Exception("Put only currency code as an argument");
        if (!args[0].matches("[A-Z]{3}")) throw new Exception("Currency code should be 3 uppercase letters.");
        if (!DataRepo.getInstance().exchangeRateList.stream().anyMatch(x -> x.getCode().equals(args[0])))
            throw new Exception("Currency code doesn't exist in file.");
    }
}
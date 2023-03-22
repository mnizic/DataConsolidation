package com.github.mnizic.dataconsolidation.writer;

import com.github.mnizic.dataconsolidation.model.ExchangeRate;

public interface Writer {
    void WriteData(String fileName, ExchangeRate exchangeRate);
}

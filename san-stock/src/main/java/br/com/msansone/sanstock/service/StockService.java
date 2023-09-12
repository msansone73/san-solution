package br.com.msansone.sanstock.service;

import br.com.msansone.sanstock.model.Stock;

public interface StockService {
    Stock findStock(String tickerType, String ticker);
}

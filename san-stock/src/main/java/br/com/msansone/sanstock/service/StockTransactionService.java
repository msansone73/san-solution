package br.com.msansone.sanstock.service;

import br.com.msansone.sanstock.model.StockTransaction;

import java.util.List;

public interface StockTransactionService {
    List<StockTransaction> getAll();

    StockTransaction add(StockTransaction transaction);
}

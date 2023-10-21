package br.com.msansone.sanstock.service;

import br.com.msansone.sanstock.model.Stock;
import br.com.msansone.sanstock.model.StockTransaction;
import br.com.msansone.sanstock.repository.StockRepository;
import br.com.msansone.sanstock.repository.StockResearch;
import br.com.msansone.sanstock.repository.StockTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockTransactionServiceImpl implements  StockTransactionService{

    @Autowired
    StockTransactionRepository repository;
    @Autowired
    StockRepository stockRepository;
    @Autowired
    StockResearch stockResearch;
    @Override
    public List<StockTransaction> getAll() {
        return repository.findAll();
    }

    @Override
    public StockTransaction add(StockTransaction transaction) {
        Optional<Stock> stock = stockRepository.findByTicker(transaction.getStock().getTicker());
        if(stock.isPresent()){
            transaction.setStock(stock.get());
        } else {
            Stock stk = stockResearch.getStockInfo(transaction.getTipo(), transaction.getStock().getTicker());
            transaction.setStock(stk);
        }
        return repository.save(transaction);
    }
}

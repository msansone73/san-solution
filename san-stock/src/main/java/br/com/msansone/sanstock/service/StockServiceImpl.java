package br.com.msansone.sanstock.service;

import br.com.msansone.sanstock.model.Stock;
import br.com.msansone.sanstock.repository.StockRepository;
import br.com.msansone.sanstock.repository.StockResearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service

public class StockServiceImpl implements  StockService{

    @Autowired
    StockRepository stockRepository;
    @Autowired
    StockResearch stockResearch;

    public Stock findStock(String tickerType, String ticker) {
        Optional<Stock> stockdb = stockRepository.findByTicker(ticker);
        if (stockdb.isPresent()) {
            Period per = Period.between(stockdb.get().getLastUpdate().toLocalDate(),LocalDate.now());
            if (per.getDays() <= 5) {
                return stockdb.get();
            } else {
                return getStockFromWs(tickerType, ticker);
            }
        } else {
            return getStockFromWs(tickerType, ticker);
        }
    }

    private Stock getStockFromWs(String tickerType, String ticker) {
        Stock stockws = stockResearch.getStockInfo(tickerType, ticker);
        stockRepository.save(stockws);
        return stockws;
    }

}

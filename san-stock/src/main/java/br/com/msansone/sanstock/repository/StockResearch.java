package br.com.msansone.sanstock.repository;

import br.com.msansone.sanstock.model.Stock;
import org.springframework.stereotype.Repository;

@Repository
public interface StockResearch {

    Stock  getStockInfo(
            String stockType,
            String stock);
}

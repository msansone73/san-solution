package br.com.msansone.sanstock.controller;

import br.com.msansone.sanstock.model.Stock;
import br.com.msansone.sanstock.repository.StockResearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired StockResearch stockResearch;

    @GetMapping("/{stkType}/{stock}")
    public ResponseEntity<Stock> getStock(
            @PathVariable String stock,
            @PathVariable String stkType
    ) {
        Stock ret = stockResearch.getStockInfo(stkType, stock);
        return ResponseEntity.ok(ret);
    }

}

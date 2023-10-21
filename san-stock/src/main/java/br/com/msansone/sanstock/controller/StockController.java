package br.com.msansone.sanstock.controller;

import br.com.msansone.sanstock.model.Stock;
import br.com.msansone.sanstock.model.StockTransaction;
import br.com.msansone.sanstock.service.StockService;
import br.com.msansone.sanstock.service.StockTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    StockService stockService;

    @Autowired
    StockTransactionService stockTransactionService;

    @GetMapping("/{stkType}/{stock}")
    public ResponseEntity<Stock> getStock(
            @PathVariable String stock,
            @PathVariable String stkType
    ) {
        Stock ret = stockService.findStock(stkType, stock);
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/transaction")
    public ResponseEntity<List<StockTransaction>> getAllTansactions(){
        List<StockTransaction> transactions= stockTransactionService.getAll();
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/transaction")
    public ResponseEntity<StockTransaction> addTransacion(@RequestBody StockTransaction transaction){
        StockTransaction addedTransaction= stockTransactionService.add(transaction);
        return ResponseEntity.ok(addedTransaction);
    }

}

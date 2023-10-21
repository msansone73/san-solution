package br.com.msansone.sanstock.repository;

import br.com.msansone.sanstock.model.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long> {
}

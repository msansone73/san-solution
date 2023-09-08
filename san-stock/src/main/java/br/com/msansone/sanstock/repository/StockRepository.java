package br.com.msansone.sanstock.repository;

import br.com.msansone.sanstock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Long> {
}

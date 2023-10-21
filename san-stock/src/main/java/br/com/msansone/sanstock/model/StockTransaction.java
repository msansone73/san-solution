package br.com.msansone.sanstock.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class StockTransaction {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Stock stock;

    String tipo;
    LocalDate purchaseDate;
    Long quantity;
    Double unitPrice;

    public StockTransaction() {}

    public StockTransaction(Stock stock, String tipo, LocalDate purchaseDate, Long quantity, Double unitPrice) {
        this.stock = stock;
        this.tipo = tipo;
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}

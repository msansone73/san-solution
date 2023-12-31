package br.com.msansone.sanstock.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Dividend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String stock;
    private String type;
    private Date dateCom;
    private Date datePag;
    private BigDecimal valor;

    public Dividend() {
    }

    public Dividend(String stock, String type, Date dateCom, Date datePag, BigDecimal valor) {
        this.stock = stock;
        this.type = type;
        this.dateCom = dateCom;
        this.datePag = datePag;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateCom() {
        return dateCom;
    }

    public void setDateCom(Date dateCom) {
        this.dateCom = dateCom;
    }

    public Date getDatePag() {
        return datePag;
    }

    public void setDatePag(Date datePag) {
        this.datePag = datePag;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}

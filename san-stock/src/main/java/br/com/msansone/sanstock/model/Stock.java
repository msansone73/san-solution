package br.com.msansone.sanstock.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String ticker;
    String name;
    String valorAtual;
    String dividendYield;
    String pvp;
    String valorizacao12M;
    String pl;

    @OneToMany
    @JoinColumn(name = "stock_id") //A referência da tabela "Dividend" para a tabela "St)
    List<Dividend> dividends;

    public Stock() {
    }

    public Stock(String ticker, String name, String valorAtual, String dividendYield, String pvp, String valorizacao12M, String pl, List<Dividend> dividends) {
        this.ticker = ticker;
        this.name = name;
        this.valorAtual = valorAtual;
        this.dividendYield = dividendYield;
        this.pvp = pvp;
        this.valorizacao12M = valorizacao12M;
        this.pl = pl;
        this.dividends = dividends;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(String valorAtual) {
        this.valorAtual = valorAtual;
    }

    public String getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(String dividendYield) {
        this.dividendYield = dividendYield;
    }

    public String getPvp() {
        return pvp;
    }

    public void setPvp(String pvp) {
        this.pvp = pvp;
    }

    public String getValorizacao12M() {
        return valorizacao12M;
    }

    public void setValorizacao12M(String valorizacao12M) {
        this.valorizacao12M = valorizacao12M;
    }

    public String getPl() {
        return pl;
    }

    public void setPl(String pl) {
        this.pl = pl;
    }

    public List<Dividend> getDividends() {
        return dividends;
    }

    public void setDividends(List<Dividend> dividends) {
        this.dividends = dividends;
    }
}

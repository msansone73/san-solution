package br.com.msansone.sanstock.repository;

import br.com.msansone.sanstock.model.Dividend;
import br.com.msansone.sanstock.model.Stock;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StockResearchI10 implements StockResearch {

    private final String URL_BASE= "https://investidor10.com.br/%s/%s/";
    @Autowired
    StockRepository stockRepository;

    public Stock getStockInfo(
            String stockType,
            String stock
    ){



        try {

            Stock stockInfo = new Stock();

            String url= String.format(URL_BASE,stockType,stock);
            Document doc = Jsoup.connect(url).get();
            stockInfo.setTicker(stock.toUpperCase());

            stockInfo.setValorAtual(getValStockAtualFromDoc(doc,"_card cotacao"));
            stockInfo.setName(getElementByClass(doc,"name-company"));
            stockInfo.setPvp(getValoresFromDoc(doc,"_card vp"));
            stockInfo.setDividendYield(getValoresFromDoc(doc,"_card dy"));
            stockInfo.setPl(getValoresFromDoc(doc,"_card val"));
            stockInfo.setValorizacao12M(getValoresFromTitle(doc,"Valorização (12M)"));
            stockInfo.setDividends(getDividendsFromDoc(doc, stockInfo.getTicker()));

            return stockInfo;
        } catch (IOException | ParseException e) {
            //todo: add error log
            return null;
        }
    }


    private static String getValStockAtualFromDoc(Document doc, String title) {
        String valor = doc.getElementsByClass(title).get(0).getElementsByClass("value").get(0).text();
        return valor;
    }
    private static String getElementByClass(Document doc, String title) {
        String valor = doc.getElementsByClass(title).get(0).text();
        return valor;
    }
    private static String getValoresFromDoc(Document doc, String title) {
        String valor = doc.getElementsByClass(title).get(0).getElementsByClass("_card-body").get(0).text();
        return valor;
    }
    private static String getValoresFromTitle(Document doc, String title) {
        String valor= doc.getElementsByAttributeValue("title",title).get(0).parent().parent().parent().getElementsByClass("_card-body").select("span").text();
        return valor;
    }
    private List<Dividend> getDividendsFromDoc(Document doc, String ticker) throws ParseException {
        List<Dividend> dividends = new ArrayList<>();

        //Document doc = stockRepository.getStockDocument(stockTypes, stock);

        Elements element_even = doc.getElementById("dividends-section").
                getElementsByClass("visible-even");
        Elements element_odd = doc.getElementById("dividends-section").
                getElementsByClass("visible-odd");


        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        for (Element el: element_even) {
            Dividend div = new Dividend(
                    ticker,
                    el.getElementsByClass("text-center").get(0).text(),
                    dateFormat.parse(el.getElementsByClass("text-center").get(1).text()),
                    dateFormat.parse(el.getElementsByClass("text-center").get(2).text()),
                    new BigDecimal(el.getElementsByClass("text-center").get(3).text().replace(",",".")));

            dividends.add(div);
        }
        for (Element el: element_odd) {
            Dividend div = new Dividend(
                    ticker,
                    el.getElementsByClass("text-center").get(0).text(),
                    dateFormat.parse(el.getElementsByClass("text-center").get(1).text()),
                    dateFormat.parse(el.getElementsByClass("text-center").get(2).text()),
                    new BigDecimal(el.getElementsByClass("text-center").get(3).text().replace(",",".")));

            dividends.add(div);
        }

        return dividends.stream().sorted(Comparator.comparing(Dividend::getDatePag).reversed()).collect(Collectors.toList());

    }


}

package com.sainsburys.scraper.data;

import com.sainsburys.scraper.client.ScraperClient;
import com.sainsburys.scraper.exceptions.ScraperException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsService {

    private ScraperClient client;

    public ProductsService(ScraperClient client) {
        this.client = client;
    }

    public List<Product> getProducts(String url) {
        Document document = getDocument(url);
        Elements productData = document.select("div .product");
        return parseData(productData);
    }

    private Document getDocument(String url) {
        Document document;
        try {
            document = client.getDocument(url);

        } catch (IOException e) {
            String msg = String.format("Unable to retrieve product data - %s", e);
            throw new ScraperException(msg, e);
        }
        return document;
    }

    private List<Product> parseData(Elements productData) {
        return productData.stream()
                .map(e -> new Product.Builder()
                        .title(getTitle(e))
                        .pricePerUnit(getPricePerUnit(e))
                        .pricePerMeasure(getPricePerMeasure(e))
                        .description(getDescription(e))
                        .build())
                .collect(Collectors.toList());
    }

    private String getTitle(Element data) {
        return data.select("div .productInfo").select("a").text();
    }

    private Double getPricePerUnit(Element data) {
        return Double.valueOf(cleanPrice(data, ".pricePerUnit", "/unit"));
    }

    private Double getPricePerMeasure(Element data) {
        return Double.valueOf(cleanPrice(data, ".pricePerMeasure", "/kg"));
    }

    private String getDescription(Element data) {
        String productInfoUrl = data.select("div .productInfo").select("a").first().attr("abs:href");
        try {
            Document productInfoData = client.getDocument(productInfoUrl);
            return productInfoData.select("div .productText").get(0).text();
        } catch (IOException e) {
            return "";
        }
    }

    private String cleanPrice(Element data, String selector, String unit) {
        String rawPrice = data.select("div " + selector).text();
        return rawPrice.replace("£", "").replace(unit, "");
    }
}

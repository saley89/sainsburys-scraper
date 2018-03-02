package com.sainsburys.scraper.service;

import com.sainsburys.scraper.client.ScraperClient;
import com.sainsburys.scraper.domain.Product;
import com.sainsburys.scraper.exceptions.ScraperException;
import com.sainsburys.scraper.factory.products.ProductData;
import com.sainsburys.scraper.factory.products.ProductFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsService {

    private ScraperClient client;
    private final ProductFactory productFactory;

    public ProductsService(ScraperClient client) {
        productFactory = new ProductFactory();
        this.client = client;
    }

    public List<Product> getProducts(String url) {
        Document document = getDocument(url);
        Elements productData = document.select("div .product");
        return parseData(productData);
    }

    private Document getDocument(String url) {
        try {
            return client.getDocument(url);
        } catch (IOException e) {
            String msg = String.format("Unable to retrieve product data - %s", e);
            throw new ScraperException(msg, e);
        }
    }

    private List<Product> parseData(Elements allProducts) {
        return allProducts.stream()
                .map(productData -> {
                    removeCrossSellItems(productData);
                    String productInfoUrl = productData.select("div .productInfo").select("a").first().attr("abs:href");
                    Document productInfo = getDocument(productInfoUrl);
                    return productFactory.create(new ProductData(productData, productInfo));
                })
                .collect(Collectors.toList());
    }

    private void removeCrossSellItems(Element data) {
        data.select(".crossSell").remove();
    }
}

package com.sainsburys.scraper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sainsburys.scraper.domain.Product;
import com.sainsburys.scraper.domain.Results;
import com.sainsburys.scraper.service.ProductsService;

import java.io.IOException;
import java.util.List;

public class ScraperController {


    private final ObjectMapper objectMapper;
    private final ProductsService productsService;

    public ScraperController(ObjectMapper objectMapper, ProductsService productsService) {
        this.objectMapper = objectMapper;
        this.productsService = productsService;
    }

    public String scrapeProducts(String url) {
        List<Product> products = productsService.getProducts(url);
        Double total = products.stream()
                .mapToDouble(Product::unitPrice)
                .sum();
        Results results = new Results(products, total);
        try {
            return objectMapper.writeValueAsString(results);
        } catch (IOException e) {
            return String.format("Unable to retrieve product data - %s", e);
        }
    }
}

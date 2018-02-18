package com.sainsburys.scraper.factory.products;

import com.sainsburys.scraper.domain.Product;
import org.jsoup.nodes.Element;

import java.util.Optional;

public class ProductFactory  {

    public Product create(Element productData, Element productInfoData) {
        return new Product.Builder()
                .title(getTitle(productData))
                .unitPrice(getUnitPrice(productData))
                .kcalPer100g(getKcalPer100g(productInfoData))
                .description(getDescription(productInfoData))
                .build();
    }

    private String getTitle(Element data) {
        return data.select("div .productInfo").select("a").text();
    }

    private Double getUnitPrice(Element data) {
        return Double.valueOf(cleanPrice(data, ".pricePerUnit", "/unit"));
    }

    private Integer getKcalPer100g(Element data) {
        Optional<Element> kcal = data.select(".nutritionTable tr td").stream()
                .filter(e -> e.text().contains("kcal"))
                .findFirst();
        return kcal.map(element -> Integer.valueOf(element.text().replace("kcal", ""))).orElse(null);
    }

    private String getDescription(Element data) {
        return data.select("div .productText").get(0).text();
    }

    private String cleanPrice(Element data, String selector, String unit) {
        String rawPrice = data.select("div " + selector).text();
        return rawPrice.replace("£", "").replace(unit, "");
    }
}

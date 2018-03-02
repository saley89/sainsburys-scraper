package com.sainsburys.scraper.factory.products;

import com.sainsburys.scraper.domain.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ProductFactoryTest {

    private Product product;

    @Before
    public void setUp() throws IOException {
        File products = new File("src/test/resources/test_products.html");
        File product1 = new File("src/test/resources/test_product_1_info.html");

        Element productData = Jsoup.parse(products, "UTF-8", "http://scraper.com").select("div .product").get(0);
        Document productInfoData = Jsoup.parse(product1, "UTF-8", "http://scraper.com/product1-url");

        ProductFactory productFactory = new ProductFactory();
        product = productFactory.create(new ProductData(productData, productInfoData));
    }

    @Test
    public void shouldReturnProductWithTitle() {
        assertThat(product.getTitle()).isEqualTo("Product 1");
    }

    @Test
    public void shouldReturnProductWithUnitPrice() {
        assertThat(product.unitPrice()).isEqualTo(9.99);
    }

    @Test
    public void shouldReturnProductWithKcalPer100g() {
        assertThat(product.kcalPer100g()).isEqualTo(33);
    }

    @Test
    public void shouldReturnProductWithDescription() {
        assertThat(product.getDescription()).isEqualTo("by Sainsbury's product 1 description");
    }
}

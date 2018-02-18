package com.sainsburys.scraper.service;

import com.sainsburys.scraper.client.ScraperClient;
import com.sainsburys.scraper.domain.Product;
import com.sainsburys.scraper.exceptions.ScraperException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductsServiceTest {

    private static final String PRODUCTS_URL = "http://scraper.com/";
    private static final String PRODUCT1_URL = "http://scraper.com/product1-url";
    private static final String PRODUCT2_URL = "http://scraper.com/product2-url";

    @Mock
    private ScraperClient scraperClient;

    private ProductsService service;

    @Before
    public void setUp() throws IOException {
        File products = new File("src/test/resources/test_products.html");
        File product1 = new File("src/test/resources/test_product_1_info.html");
        File product2 = new File("src/test/resources/test_product_2_info.html");

        Document productsDoc = Jsoup.parse(products, "UTF-8", PRODUCTS_URL);
        Document product1Doc = Jsoup.parse(product1, "UTF-8", PRODUCT1_URL);
        Document product2Doc = Jsoup.parse(product2, "UTF-8", PRODUCT2_URL);

        when(scraperClient.getDocument(PRODUCTS_URL)).thenReturn(productsDoc);
        when(scraperClient.getDocument(PRODUCT1_URL)).thenReturn(product1Doc);
        when(scraperClient.getDocument(PRODUCT2_URL)).thenReturn(product2Doc);

        service = new ProductsService(scraperClient);
    }

    @Test
    public void shouldReturnListOfFilteredProducts() {
        List<Product> products = service.getProducts(PRODUCTS_URL);
        assertThat(products.size()).isEqualTo(2);
        assertThat(products.get(0)).isInstanceOf(Product.class);
    }

    @Test
    public void shouldThrowExceptionIfCannotGetDocument() throws IOException {
        when(scraperClient.getDocument(PRODUCTS_URL)).thenThrow(new UnknownHostException(PRODUCTS_URL));

        Throwable exception = catchThrowable(() -> service.getProducts(PRODUCTS_URL));

        assertThat(exception).isNotNull();
        assertThat(exception).isInstanceOf(ScraperException.class);
        assertThat(exception.getMessage()).isEqualTo("Unable to retrieve product data - java.net.UnknownHostException: http://scraper.com/");
    }

    @Test
    public void shouldReturnEmptyStringIfUnableToGetDescriptionFromProductInfoPage() throws IOException {
        when(scraperClient.getDocument(PRODUCT1_URL)).thenThrow(new UnknownHostException(PRODUCTS_URL));

        Throwable exception = catchThrowable(() -> service.getProducts(PRODUCTS_URL));

        assertThat(exception).isNotNull();
        assertThat(exception).isInstanceOf(ScraperException.class);
        assertThat(exception.getMessage()).isEqualTo("Unable to retrieve product data - java.net.UnknownHostException: http://scraper.com/");
    }
}

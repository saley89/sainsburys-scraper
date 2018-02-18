package com.sainsburys.scraper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sainsburys.scraper.domain.Product;
import com.sainsburys.scraper.service.ProductsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScraperControllerTest {

    private static final String SOME_URL = "some-url";

    @Mock
    private ProductsService productsService;
    private ScraperController controller;

    @Before
    public void setUp() {
        controller = new ScraperController(new ObjectMapper(), productsService);
    }

    @Test
    public void shouldReturnJsonStructureOfProductResults() {
        String expectedOutput = "{\"results\":[{\"title\":\"product1\",\"description\":\"some product\",\"unit_price\":1.99,\"kcal_per_100g\":10},{\"title\":\"product2\",\"description\":\"other product\",\"unit_price\":0.50}],\"total\":2.49}";
        Product product1 = new Product.Builder().title("product1").kcalPer100g(10).unitPrice(1.99).description("some product").build();
        Product product2 = new Product.Builder().title("product2").unitPrice(0.50).description("other product").build();
        when(productsService.getProducts(SOME_URL)).thenReturn(asList(product1, product2));

        String output = controller.scrapeProducts(SOME_URL);

        assertThat(output).isEqualTo(expectedOutput);
    }
}

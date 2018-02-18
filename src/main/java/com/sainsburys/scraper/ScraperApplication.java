package com.sainsburys.scraper;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sainsburys.scraper.client.ScraperClient;
import com.sainsburys.scraper.service.ProductsService;

class ScraperApplication {
    @Parameter(names={"--url", "-u"}, description = "URL to scrape for information")
    private String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

    @Parameter(names = "--help", help = true)
    private boolean help = false;

    public static void main(String ... argv) {
        ScraperApplication main = new ScraperApplication();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(argv);
        if (main.help) {
            JCommander jCommander = new JCommander(main);
            jCommander.setProgramName("sainsburys-scraper");
            jCommander.usage();
            return;
        }
        main.run();
    }

    public void run() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            ProductsService productsService = new ProductsService(new ScraperClient());
            String output = new ScraperController(objectMapper, productsService).scrapeProducts(url);
            System.out.println(output);
        } catch (Exception e) {
            System.out.println("Unexpected error occurred - " + e);
        }

    }
}
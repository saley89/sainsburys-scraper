package com.sainsburys.scraper.client;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Optional;

public class ScraperClient {

    public Optional<Document> getDocument(String url) {
        try {
            Document value = Jsoup.connect(url).get();
            return Optional.of(value);
        } catch (IOException e) {
            System.out.println("Error retrieving document: " + e.getMessage());
        }
        return Optional.empty();
    }
}

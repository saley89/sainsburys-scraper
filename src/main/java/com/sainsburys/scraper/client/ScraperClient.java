package com.sainsburys.scraper.client;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ScraperClient {

    public Document getDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}

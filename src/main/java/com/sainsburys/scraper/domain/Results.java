package com.sainsburys.scraper.domain;

import java.util.List;

public class Results {

    public List<Product> results;
    public Double total;

    public Results(List<Product> results, Double total) {
        this.results = results;
        this.total = total;
    }
}

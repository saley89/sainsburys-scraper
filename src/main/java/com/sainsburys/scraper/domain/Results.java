package com.sainsburys.scraper.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

public class Results {

    public List<Product> results;

    @JsonSerialize(using=PriceJsonSerializer.class)
    public Double total;

    public Results(List<Product> results, Double total) {
        this.results = results;
        this.total = total;
    }
}

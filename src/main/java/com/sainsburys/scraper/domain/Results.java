package com.sainsburys.scraper.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

public class Results {

    public List<Item> results;

    @JsonSerialize(using=PriceJsonSerializer.class)
    public Double total;

    public Results(List<Item> results, Double total) {
        this.results = results;
        this.total = total;
    }
}

package com.sainsburys.scraper.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(Include.NON_NULL)
public class Product {

    private final String title;
    private final Double unitPrice;
    private final Integer kcalPer100g;
    private final String description;

    @JsonProperty("unit_price")
    @JsonSerialize(using=PriceJsonSerializer.class)
    public Double unitPrice() {
        return unitPrice;
    }

    @JsonProperty("kcal_per_100g")
    public Integer kcalPer100g() {
        return kcalPer100g;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public static class Builder {
        private String title;
        private Double unitPrice;
        private Integer kcalPer100g;
        private String description;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder unitPrice(Double pricePerUnit) {
            this.unitPrice = pricePerUnit;
            return this;
        }

        public Builder kcalPer100g(Integer pricePerMeasure) {
            this.kcalPer100g = pricePerMeasure;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    public Product(Builder b) {
        this.title = b.title;
        this.unitPrice = b.unitPrice;
        this.kcalPer100g = b.kcalPer100g;
        this.description = b.description;
    }

}

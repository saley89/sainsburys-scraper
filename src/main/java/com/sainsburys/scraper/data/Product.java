package com.sainsburys.scraper.data;

public class Product {

    private final String title;
    private final Double pricePerUnit;
    private final Double pricePerMeasure;
    private final String description;

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public Double getPricePerMeasure() {
        return pricePerMeasure;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public static class Builder {
        private String title;
        private Double pricePerUnit;
        private Double pricePerMeasure;
        private String description;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder pricePerUnit(Double pricePerUnit) {
            this.pricePerUnit = pricePerUnit;
            return this;
        }

        public Builder pricePerMeasure(Double pricePerMeasure) {
            this.pricePerMeasure = pricePerMeasure;
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
        this.pricePerUnit = b.pricePerUnit;
        this.pricePerMeasure = b.pricePerMeasure;
        this.description = b.description;
    }

}

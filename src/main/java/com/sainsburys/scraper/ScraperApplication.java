package com.sainsburys.scraper;

public class ScraperApplication {

    public String run() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        String message = new ScraperApplication().run();
        System.out.println(message);
    }
}
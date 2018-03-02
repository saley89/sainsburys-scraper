package com.sainsburys.scraper.factory.products;

import com.sainsburys.scraper.factory.ScrapeData;
import org.jsoup.nodes.Element;

public class ProductData implements ScrapeData {

    private Element infoData;
    private Element descriptionData;

    public ProductData(Element infoData, Element descriptionData) {
        this.infoData = infoData;
        this.descriptionData = descriptionData;
    }

    public Element getInfoData() {
        return infoData;
    }

    public Element getDescriptionData() {
        return descriptionData;
    }
}

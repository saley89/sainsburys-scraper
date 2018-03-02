package com.sainsburys.scraper.factory;

import com.sainsburys.scraper.domain.Item;

public interface ScrapeFactory {

    Item create(ScrapeData data);

}

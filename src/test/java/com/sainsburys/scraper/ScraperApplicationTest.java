package com.sainsburys.scraper;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class ScraperApplicationTest {

    private ScraperApplication scraperApplication;

    @Before
    public void setUp() {
        scraperApplication = new ScraperApplication();
    }

    @Test
    public void shouldReturnGreetingFromRun() {
        assertThat(scraperApplication.run()).isEqualTo("Hello World!");
    }
}
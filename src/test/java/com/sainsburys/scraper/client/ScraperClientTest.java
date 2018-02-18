package com.sainsburys.scraper.client;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.net.UnknownHostException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Jsoup.class)
public class ScraperClientTest {

    public static final String URL = "some-url";
    private ScraperClient client;
    private Connection connection;

    @Before
    public void setUp() {
        PowerMockito.mockStatic(Jsoup.class);
        connection = mock(Connection.class);
        when(Jsoup.connect(anyString())).thenReturn(connection);
        client = new ScraperClient();
    }

    @Test
    public void shouldReturnDocumentIfConnectionSuccessful() throws IOException {
        Document mockDocument = mock(Document.class);
        when(connection.get()).thenReturn(mockDocument);

        Document document = client.getDocument(URL);

        assertThat(document).isEqualTo(mockDocument);
    }

    @Test
    public void shouldThrowExceptionIfConnectionFails() throws IOException {
        when(connection.get()).thenThrow(new UnknownHostException(URL));

        Throwable exception = catchThrowable(() -> client.getDocument(URL));

        assertThat(exception).isNotNull();
        assertThat(exception).isInstanceOf(UnknownHostException.class);
    }
}

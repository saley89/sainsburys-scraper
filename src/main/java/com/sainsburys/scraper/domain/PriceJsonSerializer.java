package com.sainsburys.scraper.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;

public class PriceJsonSerializer extends JsonSerializer<Double> {

    @Override
    public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        BigDecimal b = new BigDecimal(value, MathContext.DECIMAL64);
        jgen.writeNumber(b.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
    }
}
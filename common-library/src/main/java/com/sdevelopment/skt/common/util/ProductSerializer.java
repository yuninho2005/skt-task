package com.sdevelopment.skt.common.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.sdevelopment.skt.common.domain.Product;

import java.io.IOException;

public class ProductSerializer extends StdSerializer<Product> {

    public ProductSerializer() {
        this(null);
    }

    public ProductSerializer(Class<Product> t) {
        super(t);
    }

    @Override
    public void serialize(
            Product product, JsonGenerator jsonGenerator, SerializerProvider serializer) {
        try {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", product.getName());
            jsonGenerator.writeEndObject();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

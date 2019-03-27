package com.sdevelopment.skt.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.sdevelopment.skt.common.domain.Product;

import java.io.IOException;

public class ProductDeserializer extends StdDeserializer<Product> {

    public ProductDeserializer() {
        this(null);
    }

    public ProductDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Product deserialize(JsonParser parser, DeserializationContext deserializer) {
        Product product = new Product();
        ObjectCodec codec = parser.getCodec();
        JsonNode node = null;

        try {
            node = codec.readTree(parser);

            JsonNode nameNode = node.get("name");
            String name = nameNode.asText();
            product.setName(name);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return product;
    }
}

package com.ex.popply.common.deserializer;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Arrays;

public class CustomEnumDeserializer extends StdDeserializer<Enum<?>>
        implements ContextualDeserializer {

    public CustomEnumDeserializer() {
        this(null);
    }

    protected CustomEnumDeserializer(Class<?> vc) {
        super(vc);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Enum<?> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode jsonNode = jp.getCodec().readTree(jp);
        final String text = jsonNode.asText();
        final Class<? extends Enum> enumType = (Class<? extends Enum>) this._valueClass;
        if (enumType == null) return null;
        return Arrays.stream(enumType.getEnumConstants())
                .filter(value -> value.name().equals(text))
                .findAny()
                .orElse(null);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property)
            throws JsonMappingException {
        return new CustomEnumDeserializer(property.getType().getRawClass());
    }
}

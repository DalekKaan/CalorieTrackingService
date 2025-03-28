package ru.r1b.calorietrackingservice.model.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Converter to present Map as JSON database field
 * @param <K> json key type (must be serializable)
 * @param <V> json value type
 */
@Converter
public abstract class CommonHashMapConverter<K, V> implements AttributeConverter<Map<K, V>, String> {

    private final ObjectMapper objectMapper;
    private final Logger logger;
    private final TypeReference<HashMap<K, V>> typeReference = new TypeReference<>() {
    };

    public CommonHashMapConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.logger = LoggerFactory.getLogger(CommonHashMapConverter.class);
    }

    @Override
    public String convertToDatabaseColumn(Map<K, V> map) {

        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(map);
        } catch (final JsonProcessingException e) {
            logger.error("JSON writing error", e);
        }

        return jsonString;
    }

    @Override
    public Map<K, V> convertToEntityAttribute(String jsonString) {

        Map<K, V> out = null;
        try {
            out = objectMapper.readValue(jsonString, typeReference);
        } catch (final IOException e) {
            logger.error("JSON reading error", e);
        }

        return out;
    }
}

package ru.r1b.calorietrackingservice.model.converter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

public class EntityCountConverter extends CommonHashMapConverter<UUID, Integer> {
    public EntityCountConverter(ObjectMapper objectMapper) {
        super(objectMapper);
    }
}

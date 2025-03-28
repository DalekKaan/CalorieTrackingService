package ru.r1b.calorietrackingservice.scheme.personstatistics;

import ru.r1b.calorietrackingservice.model.Eating;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

public record EatingHistory(Map<LocalDate, Collection<Eating>> history) {
}

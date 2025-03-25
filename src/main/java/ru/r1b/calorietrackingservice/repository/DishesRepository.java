package ru.r1b.calorietrackingservice.repository;

import ru.r1b.calorietrackingservice.model.DataModel;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class DishesRepository implements DataRepository {
    @Override
    public DataModel getOne(UUID id) {
        // todo: to be implemented
        return null;
    }

    @Override
    public DataModel getOne(Condition condition) {
        // todo: to be implemented
        return null;
    }

    @Override
    public Collection<DataModel> getMany(Condition condition) {
        // todo: to be implemented
        return List.of();
    }
}

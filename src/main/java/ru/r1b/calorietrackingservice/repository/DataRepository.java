package ru.r1b.calorietrackingservice.repository;

import ru.r1b.calorietrackingservice.model.DataModel;

import java.util.Collection;
import java.util.UUID;

public interface DataRepository {
    public DataModel getOne(UUID id);
    public DataModel getOne(Condition condition);
    public Collection<DataModel> getMany(Condition condition); //
}

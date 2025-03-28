package ru.r1b.calorietrackingservice.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.r1b.calorietrackingservice.model.converter.EntityCountConverter;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
public class Eating implements ResourceEntity {
    // todo: validation
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Person person;
    private LocalDateTime dateTime;
    @Convert(converter = EntityCountConverter.class)
    @Column(columnDefinition = "jsonb")
    @ColumnTransformer(write = "?::jsonb")
    private Map<UUID, Integer> dishes;

    public void setUser(Person person) {
        this.person = person;
    }

    public UUID getId() {
        return id;
    }

    public Person getUser() {
        return person;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Map<UUID, Integer> getDishes() {
        return dishes;
    }
}

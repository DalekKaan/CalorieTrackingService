package ru.r1b.calorietrackingservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import ru.r1b.calorietrackingservice.model.converter.EntityCountConverter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
public class Eating implements ResourceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Person person;

    @DateTimeFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime = LocalDateTime.now();

    @Convert(converter = EntityCountConverter.class)
    @Column(columnDefinition = "jsonb")
    @ColumnTransformer(write = "?::jsonb")
    private Map<UUID, Integer> dishes = new HashMap<>();

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

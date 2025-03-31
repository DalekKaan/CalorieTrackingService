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
    // для ревью:
    // я знаю что здесь для сообщений об ошибках следовало использовать файлы локализации,
    // но в рамках тестового задания реши обойтись прямым указанием сообщения
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Person person;

    @DateTimeFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime = LocalDateTime.now();

    // для ревью:
    // хранение связи "многие-ко-многим" в поле json, как правило, является лучше использования для этого отдельной
    // связующей таблицы, так как убирает лишнее сканирование при запросах, особенно с учётом широких возможностей для
    // работы с JSON конкретно у PostgreSQL
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

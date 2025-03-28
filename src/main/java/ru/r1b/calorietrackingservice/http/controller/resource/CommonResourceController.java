package ru.r1b.calorietrackingservice.http.controller.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.r1b.calorietrackingservice.model.ResourceEntity;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
public abstract class CommonResourceController<E extends ResourceEntity> implements ResourceController<E> {

    private final JpaRepository<E, UUID> repository;

    private final ObjectMapper objectMapper;

    public CommonResourceController(JpaRepository<E, UUID> repository,
                                    ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public PagedModel<E> getAll(Pageable pageable) {
        Page<E> people = repository.findAll(pageable);
        return new PagedModel<>(people);
    }

    @Override
    public E getOne(@PathVariable UUID id) {
        Optional<E> personOptional = repository.findById(id);
        return personOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));
    }

    @Override
    public List<E> getMany(@RequestParam List<UUID> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public E create(@RequestBody E person) {
        // todo: add validation
        return repository.save(person);
    }

    @Override
    public E patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        E person = repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        objectMapper.readerForUpdating(person).readValue(patchNode);

        return repository.save(person);
    }

    @Override
    public List<UUID> patchMany(@RequestParam List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        Collection<E> people = repository.findAllById(ids);

        for (E person : people) {
            objectMapper.readerForUpdating(person).readValue(patchNode);
        }

        List<E> resultPeople = repository.saveAll(people);
        return resultPeople.stream()
                .map(E::getId)
                .toList();
    }

    @Override
    public E delete(@PathVariable UUID id) {
        E person = repository.findById(id).orElse(null);
        if (person != null) {
            repository.delete(person);
        }
        return person;
    }

    @Override
    public void deleteMany(@RequestParam List<UUID> ids) {
        repository.deleteAllById(ids);
    }
}

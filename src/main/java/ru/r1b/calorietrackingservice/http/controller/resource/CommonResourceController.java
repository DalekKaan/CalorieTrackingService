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
        Page<E> entities = repository.findAll(pageable);
        return new PagedModel<>(entities);
    }

    @Override
    public E getOne(@PathVariable UUID id) {
        Optional<E> entityOptional = repository.findById(id);
        return entityOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));
    }

    @Override
    public List<E> getMany(@RequestParam List<UUID> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public E create(@RequestBody E person) {
        return repository.save(person);
    }

    @Override
    public E patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        E entity = repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        objectMapper.readerForUpdating(entity).readValue(patchNode);

        return repository.save(entity);
    }

    @Override
    public List<UUID> patchMany(@RequestParam List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        Collection<E> entities = repository.findAllById(ids);

        for (E entity : entities) {
            objectMapper.readerForUpdating(entity).readValue(patchNode);
        }

        List<E> resultEntities = repository.saveAll(entities);
        return resultEntities.stream()
                .map(E::getId)
                .toList();
    }

    @Override
    public E delete(@PathVariable UUID id) {
        E entity = repository.findById(id).orElse(null);
        if (entity != null) {
            repository.delete(entity);
        }
        return entity;
    }

    @Override
    public void deleteMany(@RequestParam List<UUID> ids) {
        repository.deleteAllById(ids);
    }
}

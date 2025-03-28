package ru.r1b.calorietrackingservice.controller.resource;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;
import ru.r1b.calorietrackingservice.model.ResourceEntity;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ResourceController<E extends ResourceEntity> {
    @GetMapping
    PagedModel<E> getAll(Pageable pageable);

    @GetMapping("/{id}")
    E getOne(@PathVariable UUID id);

    @GetMapping("/by-ids")
    List<E> getMany(@RequestParam List<UUID> ids);

    @PostMapping
    E create(@RequestBody E person);

    @PatchMapping("/{id}")
    E patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException;

    @PatchMapping
    List<UUID> patchMany(@RequestParam List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException;

    @DeleteMapping("/{id}")
    E delete(@PathVariable UUID id);

    @DeleteMapping
    void deleteMany(@RequestParam List<UUID> ids);
}

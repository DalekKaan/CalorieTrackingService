package ru.r1b.calorietrackingservice.controller.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.r1b.calorietrackingservice.model.Dish;
import ru.r1b.calorietrackingservice.repository.DishesRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/dish")
public class DishResourceController {

    private final DishesRepository dishesRepository;

    private final ObjectMapper objectMapper;

    public DishResourceController(DishesRepository dishesRepository,
                                  ObjectMapper objectMapper) {
        this.dishesRepository = dishesRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public PagedModel<Dish> getAll(Pageable pageable) {
        Page<Dish> dishes = dishesRepository.findAll(pageable);
        return new PagedModel<>(dishes);
    }

    @GetMapping("/{id}")
    public Dish getOne(@PathVariable UUID id) {
        Optional<Dish> dishOptional = dishesRepository.findById(id);
        return dishOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));
    }

    @GetMapping("/by-ids")
    public List<Dish> getMany(@RequestParam List<UUID> ids) {
        return dishesRepository.findAllById(ids);
    }

    @PostMapping
    public Dish create(@RequestBody Dish dish) {
        // todo: validation
        return dishesRepository.save(dish);
    }

    @PatchMapping("/{id}")
    public Dish patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        // todo: validation
        Dish dish = dishesRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        objectMapper.readerForUpdating(dish).readValue(patchNode);

        return dishesRepository.save(dish);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        // todo: validation
        Collection<Dish> dishes = dishesRepository.findAllById(ids);

        for (Dish dish : dishes) {
            objectMapper.readerForUpdating(dish).readValue(patchNode);
        }

        List<Dish> resultDishes = dishesRepository.saveAll(dishes);
        return resultDishes.stream()
                .map(Dish::getId)
                .toList();
    }

    @DeleteMapping("/{id}")
    public Dish delete(@PathVariable UUID id) {
        Dish dish = dishesRepository.findById(id).orElse(null);
        if (dish != null) {
            dishesRepository.delete(dish);
        }
        return dish;
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        dishesRepository.deleteAllById(ids);
    }
}

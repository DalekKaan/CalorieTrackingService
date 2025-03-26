package ru.r1b.calorietrackingservice.controller.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.r1b.calorietrackingservice.model.Person;
import ru.r1b.calorietrackingservice.repository.PersonsRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/person")
public class PersonResourceController {

    private final PersonsRepository personsRepository;

    private final ObjectMapper objectMapper;

    public PersonResourceController(PersonsRepository personsRepository,
                                    ObjectMapper objectMapper) {
        this.personsRepository = personsRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public PagedModel<Person> getAll(Pageable pageable) {
        Page<Person> people = personsRepository.findAll(pageable);
        return new PagedModel<>(people);
    }

    @GetMapping("/{id}")
    public Person getOne(@PathVariable UUID id) {
        Optional<Person> personOptional = personsRepository.findById(id);
        return personOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));
    }

    @GetMapping("/by-ids")
    public List<Person> getMany(@RequestParam List<UUID> ids) {
        return personsRepository.findAllById(ids);
    }

    @PostMapping
    public Person create(@RequestBody Person person) {
        // todo: add validation
        return personsRepository.save(person);
    }

    @PatchMapping("/{id}")
    public Person patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        // todo: add validation
        Person person = personsRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        objectMapper.readerForUpdating(person).readValue(patchNode);

        return personsRepository.save(person);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        // todo: add validation
        Collection<Person> people = personsRepository.findAllById(ids);

        for (Person person : people) {
            objectMapper.readerForUpdating(person).readValue(patchNode);
        }

        List<Person> resultPeople = personsRepository.saveAll(people);
        return resultPeople.stream()
                .map(Person::getId)
                .toList();
    }

    @DeleteMapping("/{id}")
    public Person delete(@PathVariable UUID id) {
        Person person = personsRepository.findById(id).orElse(null);
        if (person != null) {
            personsRepository.delete(person);
        }
        return person;
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        personsRepository.deleteAllById(ids);
    }
}

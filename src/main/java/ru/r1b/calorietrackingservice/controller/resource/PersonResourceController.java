package ru.r1b.calorietrackingservice.controller.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.r1b.calorietrackingservice.model.Person;
import ru.r1b.calorietrackingservice.repository.PersonsRepository;

@RestController()
@RequestMapping("/person")
public class PersonResourceController extends CommonResourceController<Person> {
    public PersonResourceController(PersonsRepository repository, ObjectMapper objectMapper) {
        super(repository, objectMapper);
    }
}

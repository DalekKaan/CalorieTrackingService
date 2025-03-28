package ru.r1b.calorietrackingservice.http.controller.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.r1b.calorietrackingservice.model.Eating;
import ru.r1b.calorietrackingservice.repository.EatingRepository;

@RestController
@RequestMapping("/eating")
public class EatingResourceController extends CommonResourceController<Eating> {
    // todo: save nested
    public EatingResourceController(EatingRepository repository, ObjectMapper objectMapper) {
        super(repository, objectMapper);
    }
}

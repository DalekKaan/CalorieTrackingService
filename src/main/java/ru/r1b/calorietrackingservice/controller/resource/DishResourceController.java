package ru.r1b.calorietrackingservice.controller.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.r1b.calorietrackingservice.model.Dish;
import ru.r1b.calorietrackingservice.repository.DishesRepository;

@RestController
@RequestMapping("/dish")
public class DishResourceController extends CommonResourceController<Dish> {

    public DishResourceController(DishesRepository repository, ObjectMapper objectMapper) {
        super(repository, objectMapper);
    }
}

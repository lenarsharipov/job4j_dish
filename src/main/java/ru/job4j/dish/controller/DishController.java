package ru.job4j.dish.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.dish.dto.DishDTO;
import ru.job4j.dish.model.Dish;
import ru.job4j.dish.service.DishService;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/dish")
public class DishController {
    private final DishService dishService;

    @GetMapping("/all")
    public ResponseEntity<List<Dish>> findAll() {
        var body = dishService.findAll();
        return new ResponseEntity<>(
                body,
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> findById(@PathVariable int id) {
        var dish = dishService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Dish Not Found By id: " + id
                ));
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Dish> create(@Valid @RequestBody DishDTO dishDTO) {
        return new ResponseEntity<>(
                dishService.save(dishDTO),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/")
    public ResponseEntity<String> update(@Valid @RequestBody Dish dish) {
        if (dishService.findById(dish.getId()).isEmpty()) {
            throw new IllegalArgumentException("Dish Not Found By id: " + dish.getId());
        }
        ResponseEntity<String> response =
                new ResponseEntity<>("Dish updated", HttpStatus.OK);
        if (!dishService.update(dish)) {
            response = new ResponseEntity<>(
                    "Unable to update dish",
                    HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        if (dishService.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Dish Not Found By id: " + id);
        }
        ResponseEntity<String> response =
                new ResponseEntity<>(
                        "Dish deleted",
                        HttpStatus.OK);
        if (!dishService.deleteById(id)) {
            response = new ResponseEntity<>(
                    "Unable to delete Dish with id: " + id,
                    HttpStatus.NOT_FOUND);
        }
        return response;
    }

}

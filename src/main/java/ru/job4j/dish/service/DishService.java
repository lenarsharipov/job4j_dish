package ru.job4j.dish.service;

import lombok.NonNull;
import ru.job4j.dish.dto.DishDTO;
import ru.job4j.dish.model.Dish;

import java.util.List;
import java.util.Optional;

public interface DishService {
    Dish save(@NonNull DishDTO dishDTO);

    boolean update(@NonNull Dish dish);

    boolean deleteById(int id);

    Optional<Dish> findById(int id);

    List<Dish> findAll();

}

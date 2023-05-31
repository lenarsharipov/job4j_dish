package ru.job4j.dish.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.job4j.dish.dto.DishDTO;
import ru.job4j.dish.model.Dish;
import ru.job4j.dish.repository.DishRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleDishService implements DishService {
    private final DishRepository dishRepository;

    @Override
    public Dish save(@NonNull DishDTO dishDTO) {
        var dish = new Dish();
        dish.setName(dishDTO.getName());
        dish.setDescription(dishDTO.getDescription());
        return dishRepository.save(dish);
    }

    @Override
    public boolean update(@NonNull Dish dish) {
        var result = false;
        if (dishRepository.existsById(dish.getId())) {
            dishRepository.save(dish);
            result = true;
        }
        return result;
    }

    @Override
    public boolean deleteById(int id) {
        var result = false;
        if (dishRepository.existsById(id)) {
            dishRepository.deleteById(id);
            result = true;
        }
        return result;
    }

    @Override
    public Optional<Dish> findById(int id) {
        return dishRepository.findById(id);
    }

    @Override
    public List<Dish> findAll() {
        return dishRepository.findAll();
    }
}

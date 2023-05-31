package ru.job4j.dish.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DishDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;
}

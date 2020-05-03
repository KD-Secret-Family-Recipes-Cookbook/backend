package com.tjclawson.secretrecipe.repos;

import com.tjclawson.secretrecipe.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepo extends CrudRepository<Ingredient, Long> { }

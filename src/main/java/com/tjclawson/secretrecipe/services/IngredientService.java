package com.tjclawson.secretrecipe.services;

import com.tjclawson.secretrecipe.models.Ingredient;

public interface IngredientService {

    Ingredient save(Ingredient ingredient, long recipeid);

    void delete(long ingredientid, long recipeid);

    Ingredient update(Ingredient ingredient, long ingredientid, long recipeid);
}

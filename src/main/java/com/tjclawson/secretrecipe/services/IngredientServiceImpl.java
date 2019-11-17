package com.tjclawson.secretrecipe.services;

import com.tjclawson.secretrecipe.models.Ingredient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ingredientService")
public class IngredientServiceImpl implements IngredientService {

    @Override
    public Ingredient save(Ingredient ingredient) {
        Ingredient newIngredient = new Ingredient();
        newIngredient.setIngredientname(ingredient.getIngredientname());
        newIngredient.setQuantity(ingredient.getQuantity());
        newIngredient.setMeasurement(ingredient.getMeasurement());

        return null;
    }
}

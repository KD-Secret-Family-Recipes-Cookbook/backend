package com.tjclawson.secretrecipe.services;

import com.tjclawson.secretrecipe.exceptions.ResourceNotFoundException;
import com.tjclawson.secretrecipe.models.Ingredient;
import com.tjclawson.secretrecipe.models.Recipe;
import com.tjclawson.secretrecipe.repos.IngredientRepo;
import com.tjclawson.secretrecipe.repos.RecipeRepo;
import com.tjclawson.secretrecipe.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("ingredientService")
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    IngredientRepo ingredientRepo;

    @Autowired
    RecipeRepo recipeRepo;

    @Autowired
    UserAuditing userAuditing;

    @Autowired
    UserRepo userRepo;

    @Transactional
    @Override
    public Ingredient save(Ingredient ingredient, long recipeid) {
        Recipe recipe = recipeRepo.findById(recipeid).orElseThrow(() -> new ResourceNotFoundException("Recipe with id " + recipeid + " not found"));
        if (recipe.getUser().getUserid() != userRepo.findByUsername(userAuditing.getCurrentAuditor().get()).getUserid()) {
            throw new ResourceNotFoundException("Recipe with id " + recipeid + " not found");
        }
        Ingredient newIngredient = new Ingredient();
        newIngredient.setIngredientname(ingredient.getIngredientname());
        newIngredient.setQuantity(ingredient.getQuantity());
        newIngredient.setMeasurement(ingredient.getMeasurement());
        newIngredient.setRecipe(recipe);
        return ingredientRepo.save(newIngredient);
    }

    @Transactional
    @Override
    public void delete(long ingredientid, long recipeid) {
        Recipe recipe = recipeRepo.findById(recipeid).orElseThrow(() -> new ResourceNotFoundException("Recipe with id " + recipeid + " not found"));
        if (recipe.getUser().getUserid() != userRepo.findByUsername(userAuditing.getCurrentAuditor().get()).getUserid()) {
            throw new ResourceNotFoundException("Recipe with id " + recipeid + " not found");
        }
        ingredientRepo.findById(ingredientid).orElseThrow(() -> new ResourceNotFoundException("Ingredient with id " + ingredientid + " not found"));
        ingredientRepo.deleteById(ingredientid);
    }

    @Transactional
    @Override
    public Ingredient update(Ingredient ingredient, long ingredientid, long recipeid) {
        Recipe recipe = recipeRepo.findById(recipeid).orElseThrow(() -> new ResourceNotFoundException("Recipe with id " + recipeid + " not found"));
        if (recipe.getUser().getUserid() != userRepo.findByUsername(userAuditing.getCurrentAuditor().get()).getUserid()) {
            throw new ResourceNotFoundException("Permission denied");
        }
        Ingredient updateIngredient = ingredientRepo.findById(ingredientid)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient with id " + ingredientid + " not found"));
        if (ingredient.getIngredientname() != null) {
            updateIngredient.setIngredientname(ingredient.getIngredientname());
        }

        if (ingredient.getQuantity() != 0) {
            updateIngredient.setQuantity(ingredient.getQuantity());
        }

        if (ingredient.getMeasurement() != null) {
            updateIngredient.setMeasurement(ingredient.getMeasurement());
        }
        return ingredientRepo.save(updateIngredient);
    }
}

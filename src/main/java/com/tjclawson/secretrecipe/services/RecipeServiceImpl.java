package com.tjclawson.secretrecipe.services;

import com.tjclawson.secretrecipe.models.Ingredient;
import com.tjclawson.secretrecipe.models.Recipe;
import com.tjclawson.secretrecipe.models.User;
import com.tjclawson.secretrecipe.repos.IngredientRepo;
import com.tjclawson.secretrecipe.repos.RecipeRepo;
import com.tjclawson.secretrecipe.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("recipeService")
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepo recipeRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserAuditing userAuditing;

    @Autowired
    private IngredientRepo ingredientRepo;

    @Override
    public List<Recipe> findRecipesByCurrentUser() {
        User currentUser = userRepo.findByUsername(userAuditing.getCurrentAuditor().get());
        return recipeRepo.findAllByUser_Userid(currentUser.getUserid());
    }

    @Override
    public List<Recipe> findRecipeByNamelike(String name) {
        return recipeRepo.findByRecipenameContaining(name.toLowerCase());
    }

    @Override
    public Recipe findByName(String name) {
        Recipe recipe = recipeRepo.findByRecipename(name.toLowerCase());
        if (recipe != null) {
            throw new EntityNotFoundException("Recipe with name " + name + " not found");
        }
        return recipe;
    }

    @Override
    public Recipe findRecipeById(long id) {
        return recipeRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Recipe with id " + id + " not found"));
    }

    @Transactional
    @Override
    public Recipe save(Recipe recipe) {
        Recipe newRecipe = new Recipe();
        newRecipe.setRecipename(recipe.getRecipename());
        newRecipe.setSource(recipe.getSource());
        newRecipe.setCategory(recipe.getCategory());
        newRecipe.setInstructions(recipe.getInstructions());

        for (Ingredient ingredient : recipe.getIngredients()) {
            newRecipe.getIngredients().add(new Ingredient(ingredient.getIngredientname(),
                    ingredient.getQuantity(),
                    ingredient.getMeasurement(), newRecipe));
        }

        newRecipe.setUser(userRepo.findByUsername(userAuditing.getCurrentAuditor().get()));
        newRecipe.setImageurl(recipe.getImageurl());

        return recipeRepo.save(newRecipe);
    }

    @Transactional
    @Override
    public Recipe update(long recipeid, Recipe recipe) {
        Recipe updateRecipe = recipeRepo.findById(recipeid).orElseThrow(() -> new EntityNotFoundException("Recipe with id " + recipeid + "not found"));
        System.out.println(updateRecipe.getCategory());
        User currentUser = userRepo.findByUsername(userAuditing.getCurrentAuditor().get());
        if (updateRecipe.getUser().getUserid() != currentUser.getUserid()) {
            throw new EntityNotFoundException("Permission denied");
        }

        if (recipe.getRecipename() != null) {
            updateRecipe.setRecipename(recipe.getRecipename());
        }

        if (recipe.getSource() != null) {
            updateRecipe.setSource(recipe.getSource());
        }

        if (recipe.getCategory() != null) {
            updateRecipe.setCategory(recipe.getCategory());
        }

        if (recipe.getInstructions() != null) {
            updateRecipe.setInstructions(recipe.getInstructions());
        }

        if (recipe.getImageurl() != null) {
            updateRecipe.setImageurl(recipe.getImageurl());
        }

        if (recipe.getIngredients().size() > 0) {
            List<Ingredient> updateIngredients = recipe.getIngredients();
            List<Ingredient> currentIngredients = updateRecipe.getIngredients();
            for (Ingredient ing : updateIngredients) {
                if (ing.getIngredientid() != 0) {
                    Ingredient updateIngredient = ingredientRepo.findById(ing.getIngredientid()).orElseThrow();
                    updateIngredient.setIngredientname(ing.getIngredientname());
                    updateIngredient.setQuantity(ing.getQuantity());
                    updateIngredient.setMeasurement(ing.getMeasurement());
                    ingredientRepo.save(updateIngredient);
                } else {
                    Ingredient newIngredient = new Ingredient(ing.getIngredientname(), ing.getQuantity(), ing.getMeasurement(), updateRecipe);
                    updateRecipe.getIngredients().add(newIngredient);
                }
            }
        }
        return recipeRepo.save(updateRecipe);
    }

    @Transactional
    @Override
    public void delete(long recipeid) {
        Recipe recipe = recipeRepo.findById(recipeid).orElseThrow(() -> new EntityNotFoundException("Recipe with id " + recipeid + " not found"));
        if (recipe.getUser().getUserid() != userRepo.findByUsername(userAuditing.getCurrentAuditor().get()).getUserid()) {
            throw new EntityNotFoundException("Permission denied");
        }
        recipeRepo.deleteById(recipeid);
    }
}

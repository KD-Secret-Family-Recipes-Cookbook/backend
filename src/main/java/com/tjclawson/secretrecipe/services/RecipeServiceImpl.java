package com.tjclawson.secretrecipe.services;

import com.tjclawson.secretrecipe.models.Ingredient;
import com.tjclawson.secretrecipe.models.Recipe;
import com.tjclawson.secretrecipe.models.User;
import com.tjclawson.secretrecipe.repos.RecipeRepo;
import com.tjclawson.secretrecipe.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<Recipe> findRecipesByCurrentUser() {
        User currentUser = userRepo.findByUsername(userAuditing.getCurrentAuditor().get());
        return recipeRepo.findAllByUser_Userid(currentUser.getUserid());
    }

    @Override
    public List<Recipe> findRecipeByNamelike(String name) {
        return null;
    }

    @Override
    public Recipe findByName(String name) {
        return null;
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

        return recipeRepo.save(newRecipe);
    }

    @Override
    public Recipe update(long recipeid, Recipe recipe) {
        return null;
    }
}

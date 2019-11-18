package com.tjclawson.secretrecipe.services;

import com.tjclawson.secretrecipe.models.Recipe;
import com.tjclawson.secretrecipe.models.User;

import java.util.List;

public interface RecipeService {

    List<Recipe> findRecipesByCurrentUser();

    List<Recipe> findRecipeByNamelike(String name);

    List<Recipe> findRecipeByCategorylike(String category);

    Recipe findByName(String name);

    Recipe findRecipeById(long id);

    Recipe save(Recipe recipe);

    Recipe update(long recipeid, Recipe recipe);

    void delete(long recipeid);
}

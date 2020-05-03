package com.tjclawson.secretrecipe.services;

import com.tjclawson.secretrecipe.models.Recipe;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface RecipeService {

    List<Recipe> findRecipesByCurrentUser();

    List<Recipe> findRecipeByNamelike(String name);

    List<Recipe> findRecipeByCategorylike(String category);

    Recipe findByName(String name);

    Recipe findRecipeById(long id);

    Recipe save(Recipe recipe);

    Recipe update(long recipeid, Recipe recipe);

    void delete(long recipeid);

    Map uploadImage(MultipartFile file, long recipeid);
}

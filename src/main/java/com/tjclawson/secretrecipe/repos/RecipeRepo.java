package com.tjclawson.secretrecipe.repos;

import com.tjclawson.secretrecipe.models.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepo extends CrudRepository<Recipe, Long> {

    List<Recipe> findAllByUser_Userid(long userid);
}

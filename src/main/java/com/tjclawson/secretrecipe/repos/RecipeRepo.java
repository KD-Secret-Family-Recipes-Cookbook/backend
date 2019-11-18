package com.tjclawson.secretrecipe.repos;

import com.tjclawson.secretrecipe.models.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepo extends CrudRepository<Recipe, Long> {

    List<Recipe> findAllByUser_Userid(long userid);

    List<Recipe> findByRecipenameContainingAndUser_Userid(String name, long userid);

    Recipe findByRecipenameAndUser_Userid(String name, long userid);

    Recipe findByRecipeidAndUser_Userid(long recipeid, long userid);

    List<Recipe> findByCategoryContainingAndUser_Userid(String catefory, long userid);

}

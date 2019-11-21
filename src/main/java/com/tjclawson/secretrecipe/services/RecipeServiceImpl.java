package com.tjclawson.secretrecipe.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tjclawson.secretrecipe.exceptions.ResourceFoundException;
import com.tjclawson.secretrecipe.exceptions.ResourceNotFoundException;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    private String cloudName = System.getenv("CLOUDINARYNAME");
    private String cloudKey = System.getenv("CLOUDINARYKEY");
    private String cloudSecret = System.getenv("CLOUDINARYSECRET");

    public Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", cloudKey,
            "api_secret", cloudSecret
    ));

    @Override
    public List<Recipe> findRecipesByCurrentUser() {
        User currentUser = userRepo.findByUsername(userAuditing.getCurrentAuditor().get());
        return recipeRepo.findAllByUser_Userid(currentUser.getUserid());
    }

    @Override
    public List<Recipe> findRecipeByNamelike(String name) {
        User currentUser = userRepo.findByUsername(userAuditing.getCurrentAuditor().get());
        List<Recipe> recipes = recipeRepo.findByRecipenameContainingAndUser_Userid(name.toLowerCase(), currentUser.getUserid());
        if (recipes.size() == 0) {
            throw new ResourceNotFoundException("No recipes with name " + name + " found");
        }
        return recipes;
    }

    @Override
    public List<Recipe> findRecipeByCategorylike(String category) {
        User currentUser = userRepo.findByUsername(userAuditing.getCurrentAuditor().get());
        List<Recipe> recipes = recipeRepo.findByCategoryContainingAndUser_Userid(category, currentUser.getUserid());
        if (recipes.size() == 0) {
            throw new ResourceNotFoundException("No recipes with category " + category + " found");
        }
        return recipes;
    }

    @Override
    public Recipe findByName(String name) {
        User currentUser = userRepo.findByUsername(userAuditing.getCurrentAuditor().get());
        Recipe recipe = recipeRepo.findByRecipenameAndUser_Userid(name.toLowerCase(), currentUser.getUserid());
        if (recipe == null) {
            throw new ResourceNotFoundException("Recipe with name " + name + " not found");
        }
        return recipe;
    }

    @Override
    public Recipe findRecipeById(long recipeid) {
        User currentUser = userRepo.findByUsername(userAuditing.getCurrentAuditor().get());
        Recipe recipe = recipeRepo.findByRecipeidAndUser_Userid(recipeid, currentUser.getUserid());
        if (recipe == null) {
            throw new ResourceNotFoundException("Recipe with id " + recipeid + " not found");
        }
        return recipe;
    }

    @Transactional
    @Override
    public Recipe save(Recipe recipe) {
        User currentUser = userRepo.findByUsername(userAuditing.getCurrentAuditor().get());
        List<Recipe> recipes = recipeRepo.findAllByUser_Userid(currentUser.getUserid());
        for (Recipe r : recipes) {
            if (r.getRecipename().equals(recipe.getRecipename())) {
                throw new ResourceFoundException("Recipe with name " + recipe.getRecipename() + " already exists");
            }
        }
        Recipe newRecipe = new Recipe();
        if (recipe.getRecipename() == null) {
            throw new ResourceNotFoundException("You must provide a recipe name when creating a recipe");
        }
        newRecipe.setRecipename(recipe.getRecipename());

        if (recipe.getSource() != null) {
            newRecipe.setSource(recipe.getSource());
        }

        if (recipe.getCategory() != null) {
            newRecipe.setCategory(recipe.getCategory());
        }

        if (recipe.getInstructions() != null) {
            newRecipe.setInstructions(recipe.getInstructions());
        }

        if (recipe.getIngredients().size() > 0) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                newRecipe.getIngredients().add(new Ingredient(ingredient.getIngredientname(),
                        ingredient.getQuantity(),
                        ingredient.getMeasurement(), newRecipe));
            }
        }

        newRecipe.setUser(userRepo.findByUsername(userAuditing.getCurrentAuditor().get()));

        if (recipe.getImageurl() != null) {
            newRecipe.setImageurl(recipe.getImageurl());
        }

        return recipeRepo.save(newRecipe);
    }

    @Transactional
    @Override
    public Recipe update(long recipeid, Recipe recipe) {
        Recipe updateRecipe = recipeRepo.findById(recipeid).orElseThrow(() -> new ResourceNotFoundException("Recipe with id " + recipeid + "not found"));
        User currentUser = userRepo.findByUsername(userAuditing.getCurrentAuditor().get());
        if (updateRecipe.getUser().getUserid() != currentUser.getUserid()) {
            throw new ResourceNotFoundException("Recipe with id " + recipeid + "not found");
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
        Recipe recipe = recipeRepo.findById(recipeid).orElseThrow(() -> new ResourceNotFoundException("Recipe with id " + recipeid + " not found"));
        if (recipe.getUser().getUserid() != userRepo.findByUsername(userAuditing.getCurrentAuditor().get()).getUserid()) {
            throw new ResourceNotFoundException("Recipe with id " + recipeid + " not found");
        }
        recipeRepo.deleteById(recipeid);
    }

    @Transactional
    @Override
    public Map uploadImage(MultipartFile file, long recipeid) {
        Recipe recipe = recipeRepo.findById(recipeid).orElseThrow(() -> new ResourceNotFoundException("Recipe with id " + recipeid + " not found"));
        if (recipe.getUser().getUserid() != userRepo.findByUsername(userAuditing.getCurrentAuditor().get()).getUserid()) {
            throw new ResourceNotFoundException("Recipe with id " + recipeid + " not found");
        }
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        } catch (IOException e) {e.printStackTrace();
        }
        recipe.setImageurl(uploadResult.get("url").toString());
        recipeRepo.save(recipe);
        return uploadResult;
    }
}

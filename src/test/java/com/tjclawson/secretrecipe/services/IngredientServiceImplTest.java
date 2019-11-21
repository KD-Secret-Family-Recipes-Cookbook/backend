package com.tjclawson.secretrecipe.services;

import com.tjclawson.secretrecipe.SecretrecipeApplication;
import com.tjclawson.secretrecipe.models.Ingredient;
import com.tjclawson.secretrecipe.models.Recipe;
import com.tjclawson.secretrecipe.models.User;
import com.tjclawson.secretrecipe.repos.IngredientRepo;
import com.tjclawson.secretrecipe.repos.RecipeRepo;
import com.tjclawson.secretrecipe.repos.UserRepo;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecretrecipeApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IngredientServiceImplTest {

    @Autowired
    IngredientService ingredientService;

    @Autowired
    UserService userService;

    @Autowired
    RecipeService recipeService;

    @Autowired
    UserRepo userRepo;

    @MockBean
    private UserAuditing userAuditing;

    private List<Ingredient> ingredients = new ArrayList<>();
    private List<Recipe> recipes = new ArrayList<>();
    private User user = new User("username", "password", "email@email.com", recipes);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        long userid = 1;
        user = userRepo.findById(userid).orElse(new User());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void Asave() {
        Mockito.when(userAuditing.getCurrentAuditor()).thenReturn(java.util.Optional.of("test1"));
        Recipe newRecipe = recipeService.save(new Recipe("Name", "Source", "Category",
                "Instructions", ingredients, user, "URL"));
        Ingredient newIngredient = new Ingredient("Name", 5.0, "Measurement", newRecipe);
        newIngredient = ingredientService.save(newIngredient, newRecipe.getRecipeid());
        //name will be returned lowercase
        assertEquals("name", newIngredient.getIngredientname());
    }

    @Test
    public void Bupdate() {
        Mockito.when(userAuditing.getCurrentAuditor()).thenReturn(java.util.Optional.of("test1"));
        Recipe newRecipe = recipeService.save(new Recipe("Name2", "Source", "Category",
                "Instructions", ingredients, user, "URL"));
        Ingredient newIngredient = new Ingredient("Name", 5.0, "Measurement", newRecipe);
        newIngredient = ingredientService.save(newIngredient, newRecipe.getRecipeid());
        newIngredient.setIngredientname("changed");
        ingredientService.update(newIngredient, newIngredient.getIngredientid(), newRecipe.getRecipeid());
        assertEquals("changed", newIngredient.getIngredientname());
    }

    @Test
    public void Cdelete() {
        Mockito.when(userAuditing.getCurrentAuditor()).thenReturn(java.util.Optional.of("test1"));
        Recipe newRecipe = recipeService.save(new Recipe("Name3", "Source", "Category",
                "Instructions", ingredients, user, "URL"));
        Ingredient newIngredient = new Ingredient("Name", 5.0, "Measurement", newRecipe);
        newIngredient = ingredientService.save(newIngredient, newRecipe.getRecipeid());
        ingredientService.delete(newIngredient.getIngredientid(), newRecipe.getRecipeid());
        assertEquals(0, newRecipe.getIngredients().size());
    }
}
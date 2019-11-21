package com.tjclawson.secretrecipe.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjclawson.secretrecipe.SecretrecipeApplication;
import com.tjclawson.secretrecipe.models.Ingredient;
import com.tjclawson.secretrecipe.models.Recipe;
import com.tjclawson.secretrecipe.models.User;
import com.tjclawson.secretrecipe.repos.IngredientRepo;
import com.tjclawson.secretrecipe.repos.RecipeRepo;
import com.tjclawson.secretrecipe.services.IngredientService;
import com.tjclawson.secretrecipe.services.RecipeService;
import com.tjclawson.secretrecipe.services.UserAuditing;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecretrecipeApplication.class)
@WebAppConfiguration
@ContextConfiguration
public class IngredientControllerTest {

    @Autowired
    WebApplicationContext applicationContext;

    //@Autowired
    private MockMvc mockMvc;

    @Autowired
    RecipeRepo recipeRepo;

    @Autowired
    IngredientRepo ingredientRepo;

    @MockBean
    private IngredientService ingredientService;

    @MockBean
    private UserAuditing userAuditing;

    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .alwaysDo(print())
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addNewIngredient() throws Exception {

        String apiUrl = "/ingredients/recipe/1/ingredient";
        Mockito.when(userAuditing.getCurrentAuditor()).thenReturn(java.util.Optional.of("test1"));
        Recipe newRecipe = recipeRepo.findByRecipeidAndUser_Userid(1,1);
        Ingredient newIngredient = new Ingredient("Name", 5.0, "Measurement", newRecipe);

        ObjectMapper objectMapper = new ObjectMapper();
        String ingredientString = objectMapper.writeValueAsString(newIngredient);
        Mockito.when(ingredientService.save(any(Ingredient.class), eq(newRecipe.getRecipeid()))).thenReturn(newIngredient);

        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(ingredientString);
        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(print());
    }

    @Test
    public void updateIngredient() throws Exception {
        String apiUrl = "/ingredients/recipe/1/ingredient/1";
        Mockito.when(userAuditing.getCurrentAuditor()).thenReturn(java.util.Optional.of("test1"));
        Recipe newRecipe = recipeRepo.findByRecipeidAndUser_Userid(1,1);
        Ingredient newIngredient = new Ingredient("Name", 5.0, "Measurement", newRecipe);
        long ingredientId = 1;
        Ingredient myIngredient = ingredientRepo.findById(ingredientId).orElse(newIngredient);
        myIngredient.setIngredientname("change");

        ObjectMapper objectMapper = new ObjectMapper();
        String ingredientString = objectMapper.writeValueAsString(newIngredient);
        Mockito.when(ingredientService.update(any(Ingredient.class), eq(myIngredient.getIngredientid()), eq(newRecipe.getRecipeid()))).thenReturn(myIngredient);

        RequestBuilder rb = MockMvcRequestBuilders.put(apiUrl)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(ingredientString);
        mockMvc.perform(rb).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void deleteIngredient() throws Exception {

        String apiUrl = "/ingredients/recipe/{recipeid}/ingredient/{ingredientid}";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, "1", "1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(rb).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}
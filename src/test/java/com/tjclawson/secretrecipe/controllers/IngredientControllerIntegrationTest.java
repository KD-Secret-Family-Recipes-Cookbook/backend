package com.tjclawson.secretrecipe.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjclawson.secretrecipe.models.Ingredient;
import com.tjclawson.secretrecipe.models.Recipe;
import com.tjclawson.secretrecipe.repos.RecipeRepo;
import com.tjclawson.secretrecipe.services.UserAuditing;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.number.OrderingComparison.lessThan;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IngredientControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    RecipeRepo recipeRepo;

    @MockBean
    UserAuditing userAuditing;

    @Before
    public void initialiseRestAssuredMockMvcWebApplicationContext() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @Test
    public void whenMeasuredReponseTime() {
        given().when().post("/ingredients/recipe/1/ingredient").then().time(lessThan(5000L));
    }

    @Test
    public void givenPostIngredient() throws Exception {
        Recipe myRecipe = recipeRepo.findByRecipeidAndUser_Userid(1, 1);
        Ingredient myIngredient = new Ingredient("name", 2.0, "measurement", myRecipe);

        ObjectMapper mapper = new ObjectMapper();
        String ingredientString = mapper.writeValueAsString(myIngredient);

        Mockito.when(userAuditing.getCurrentAuditor()).thenReturn(java.util.Optional.of("test1"));

        given().contentType("application/json").body(ingredientString).when().post("/ingredients/recipe/1/ingredient").then().statusCode(201);
    }

    @Test
    public void givenUpdateIngredient() throws Exception {

        Recipe myRecipe = recipeRepo.findByRecipeidAndUser_Userid(1, 1);
        Ingredient myIngredient = new Ingredient("name", 2.0, "measurement", myRecipe);

        ObjectMapper mapper = new ObjectMapper();
        String ingredientString = mapper.writeValueAsString(myIngredient);

        Mockito.when(userAuditing.getCurrentAuditor()).thenReturn(java.util.Optional.of("test1"));

        given().contentType("application/json").body(ingredientString).when().put("/ingredients/recipe/1/ingredient/1").then().statusCode(200);
    }

    @Test
    public void givenDeleteIngredient() {
        Mockito.when(userAuditing.getCurrentAuditor()).thenReturn(java.util.Optional.of("test1"));
        given().when().delete("/ingredients/recipe/1/ingredient/1").then().statusCode(200);
    }
}

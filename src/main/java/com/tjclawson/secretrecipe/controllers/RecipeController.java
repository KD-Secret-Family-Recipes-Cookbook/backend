package com.tjclawson.secretrecipe.controllers;

import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tjclawson.secretrecipe.models.Recipe;
import com.tjclawson.secretrecipe.services.RecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recipes")
@Api(tags = {"RecipesEndpoints"})
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @ApiOperation(value = "Returns all user's recipes", response = Recipe.class, responseContainer = "List")
    @GetMapping(value = "/recipes", produces = {"application/json"})
    public ResponseEntity<?> listUserRecipes() {
        List<Recipe> recipes = recipeService.findRecipesByCurrentUser();
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping(value = "/recipe/namelike/{recipename}", produces = {"application/json"})
    public ResponseEntity<?> findRecipeByNamelike(@PathVariable String recipename) {
        List<Recipe> recipes = recipeService.findRecipeByNamelike(recipename);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping(value = "/{recipename}", produces = {"application/json"})
    public ResponseEntity<?> findRecipeByName(@PathVariable String recipename) {
        Recipe recipe = recipeService.findByName(recipename);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @GetMapping(value = "/recipe/{recipeid}", produces = {"application/json"})
    public ResponseEntity<?> findRecipeById(@PathVariable long recipeid) {
        Recipe recipe = recipeService.findRecipeById(recipeid);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @GetMapping(value = "/recipe/categorylike/{recipecategory}", produces = {"application/json"})
    public ResponseEntity<?> findRecipeByCategorylike(@PathVariable String recipecategory) {
        List<Recipe> recipes = recipeService.findRecipeByCategorylike(recipecategory);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @PostMapping(value = "/recipe", consumes = {"application/json"})
    public ResponseEntity<?> addNewRecipe(@Valid @RequestBody Recipe newRecipe) {
        newRecipe = recipeService.save(newRecipe);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRecipeUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{recipeid}")
                .buildAndExpand(newRecipe.getRecipeid())
                .toUri();
        responseHeaders.setLocation(newRecipeUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PostMapping(value = "/recipe/{recipeid}/uploadimage", produces = {"application/json"})
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable long recipeid) {
        Map uploadResult = recipeService.uploadImage(file, recipeid);
        return new ResponseEntity<>(uploadResult, HttpStatus.OK);
    }

    @PutMapping(value = "/recipe/{recipeid}", consumes = {"application/json"})
    public ResponseEntity<?> updateRecipe(@Valid @RequestBody Recipe updateRecipe, @PathVariable long recipeid) {
        recipeService.update(recipeid, updateRecipe);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/recipe/{recipeid}")
    public ResponseEntity<?> deleteRecipeById(@PathVariable long recipeid) {
        recipeService.delete(recipeid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

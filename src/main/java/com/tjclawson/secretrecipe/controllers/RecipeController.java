package com.tjclawson.secretrecipe.controllers;

import com.tjclawson.secretrecipe.models.Recipe;
import com.tjclawson.secretrecipe.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping(value = "/recipes", produces = {"application/json"})
    public ResponseEntity<?> listUserRecipes() {
        List<Recipe> recipes = recipeService.findRecipesByCurrentUser();
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

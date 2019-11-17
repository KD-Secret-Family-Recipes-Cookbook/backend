package com.tjclawson.secretrecipe.controllers;

import com.tjclawson.secretrecipe.models.Ingredient;
import com.tjclawson.secretrecipe.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    @PostMapping(value = "/recipe/{recipeid}/ingredient", consumes = {"application/json"})
    public ResponseEntity<?> addNewIngredient(@PathVariable long recipeid, @Valid @RequestBody Ingredient ingredient) {
        ingredient = ingredientService.save(ingredient, recipeid);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI ingredientUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{recipeid}")
                .buildAndExpand(ingredient.getIngredientid())
                .toUri();
        responseHeaders.setLocation(ingredientUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/recipe/{recipeid}/ingredient/{ingredientid}", consumes = {"application/json"})
    public ResponseEntity<?> updateIngredient(@PathVariable long recipeid, @PathVariable long ingredientid, @Valid @RequestBody Ingredient updateIngredient) {
        ingredientService.update(updateIngredient, ingredientid, recipeid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/recipe/{recipeid}/ingredient/{ingredientid}")
    public ResponseEntity<?> deleteIngredient(@PathVariable long recipeid, @PathVariable long ingredientid) {
        ingredientService.delete(ingredientid, recipeid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

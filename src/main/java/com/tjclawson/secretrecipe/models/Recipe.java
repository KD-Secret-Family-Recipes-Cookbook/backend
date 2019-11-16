package com.tjclawson.secretrecipe.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long recipeid;

    @Column(nullable = false)
    private String recipename;

    private String source;

    private String category;

    private String instructions;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<UserRecipe> userrecipes = new ArrayList<>();

    public Recipe() {
    }

    public Recipe(String recipename, String source, String category, String instructions, List<Ingredient> ingredients) {
        this.recipename = recipename;
        this.source = source;
        this.category = category;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public long getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(long recipeid) {
        this.recipeid = recipeid;
    }

    public String getRecipename() {
        return recipename;
    }

    public void setRecipename(String recipename) {
        this.recipename = recipename;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<UserRecipe> getUserrecipes() {
        return userrecipes;
    }

    public void setUserrecipes(List<UserRecipe> userrecipes) {
        this.userrecipes = userrecipes;
    }
}

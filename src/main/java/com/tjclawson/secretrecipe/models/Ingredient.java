package com.tjclawson.secretrecipe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ingredientid;

    @Column(nullable = false)
    private String ingredientname;

    private int quantity;

    private String measurement;

    @ManyToOne
    @JoinColumn(name = "recipeid")
    @JsonIgnoreProperties("recipe")
    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(String ingredientname, int quantity, String measurement, Recipe recipe) {
        this.ingredientname = ingredientname;
        this.quantity = quantity;
        this.measurement = measurement;
        this.recipe = recipe;
    }

    public long getIngredientid() {
        return ingredientid;
    }

    public void setIngredientid(long ingredientid) {
        this.ingredientid = ingredientid;
    }

    public String getIngredientname() {
        return ingredientname;
    }

    public void setIngredientname(String ingredientname) {
        this.ingredientname = ingredientname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}

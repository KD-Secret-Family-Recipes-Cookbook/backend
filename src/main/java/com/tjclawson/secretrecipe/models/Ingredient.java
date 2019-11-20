package com.tjclawson.secretrecipe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
@ApiModel(value = "Ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ingredientid;

    @Column(nullable = false)
    private String ingredientname;

    private double quantity;

    private String measurement;

    @ManyToOne
    @JoinColumn(name = "recipeid")
    @JsonIgnoreProperties({"user", "recipe"})
    @ApiModelProperty(hidden = true)
    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(String ingredientname, double quantity, String measurement, Recipe recipe) {
        this.ingredientname = ingredientname.toLowerCase();
        this.quantity = quantity;
        this.measurement = measurement.toLowerCase();
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
        this.ingredientname = ingredientname.toLowerCase();
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement.toLowerCase();
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}

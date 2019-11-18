package com.tjclawson.secretrecipe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes", uniqueConstraints = {@UniqueConstraint(columnNames = {"recipename", "userid"})})
@JsonIgnoreProperties("user")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recipeid;

    @Column(nullable = false)
    private String recipename;

    private String source;

    private String category;

    private String instructions;

    private String imageurl;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "recipe", allowSetters = true)
    private List<Ingredient> ingredients = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties("user")
    private User user;


    public Recipe() {
    }

    public Recipe(String recipename, String source, String category, String instructions, List<Ingredient> ingredients, User user, String imageurl) {
        this.recipename = recipename.toLowerCase();
        this.source = source.toLowerCase();
        this.category = category.toLowerCase();
        this.instructions = instructions;
        for (Ingredient ing : ingredients) {
            ing.setRecipe(this);
        }
        this.ingredients = ingredients;
        this.user = user;
        this.imageurl = imageurl;
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
        this.recipename = recipename.toLowerCase();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source.toLowerCase();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}

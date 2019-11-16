package com.tjclawson.secretrecipe.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "userrecipes", uniqueConstraints = {@UniqueConstraint(columnNames = {"userid", "recipeid"})})
public class UserRecipe implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "recipeid")
    private Recipe recipe;

    public UserRecipe() {
    }

    public UserRecipe(User user, Recipe recipe) {
        this.user = user;
        this.recipe = recipe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRecipe)) return false;
        UserRecipe that = (UserRecipe) o;
        return Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getRecipe(), that.getRecipe());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getRecipe());
    }
}

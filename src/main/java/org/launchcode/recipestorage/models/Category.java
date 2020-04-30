package org.launchcode.recipestorage.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category extends AbstractEntity{

    @ManyToMany(mappedBy = "categories")
    private List<Recipe> recipes = new ArrayList<>();

    public Category () {}

    public Category (List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}

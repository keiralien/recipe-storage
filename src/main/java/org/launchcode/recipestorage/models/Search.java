package org.launchcode.recipestorage.models;

import java.util.ArrayList;
import java.util.List;

public class Search {

    public static List<Recipe> find(Iterable<Recipe> allRecipes) {

        List<Recipe> results = new ArrayList<>();

        return (ArrayList<Recipe>) allRecipes;
    }

    public static List<Recipe> findByValue(Iterable<Recipe> allRecipes, String value) {

        List<Recipe> results = new ArrayList<>();

        for (Recipe recipe : allRecipes) {
            if (recipe.getName().toLowerCase().contains(value.toLowerCase())) {
                results.add(recipe);
            } else if (recipe.getDescription().toLowerCase().contains(value.toLowerCase())) {
                results.add(recipe);
            } else if (recipe.getCategories().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(recipe);
            } else if (recipe.getIngredients().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(recipe);
            } else if (recipe.getDirections().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(recipe);
            }
        }
        return results;
    }
}

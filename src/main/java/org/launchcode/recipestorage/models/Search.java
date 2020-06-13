package org.launchcode.recipestorage.models;

import java.util.ArrayList;
import java.util.List;

public class Search {

    /**
     * General search method to locate a recipe.
     *
     * @param allRecipes a list of all recipes to be searched.
     * @param searchTerm the value being searched.
     * @return return a list of recipes that match the search criteria.
     */
    public static List<Recipe> find(Iterable<Recipe> allRecipes, String searchTerm) {

        List<Recipe> results = new ArrayList<>();

        if (searchTerm.toLowerCase().equals("all")) {
            for (Recipe recipe : allRecipes) {
                results.add(recipe);
            }
        } else {
            for (Recipe recipe : allRecipes) {
                results = findByValue(allRecipes, searchTerm);
            }
        }
        return results;
    }

    /**
     * Search all recipes and all fields for the search term.
     *
     * @param allRecipes list of all recipes to search.
     * @param value      the searchTerm.
     * @return a list of recipes where the searchTerm is in one of the fields.
     */

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
//            }
        }
        return results;
    }
}
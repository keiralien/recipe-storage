package org.launchcode.recipestorage.models;

import java.util.ArrayList;
import java.util.List;

public class Search {

    /**
     * General search method to locate a recipe.
     * @param allRecipes a list of all recipes to be searched.
     * @param searchTerm the value being searched.
     * @param fieldType the field in which the value is beimg searched for.
     * @return return a list of recipes that match the search criteria.
     */
    public static List<Recipe> find(Iterable<Recipe> allRecipes, String searchTerm, String fieldType) {

        List<Recipe> results = new ArrayList<>();

        if(searchTerm.toLowerCase().equals("all")) {
            for (Recipe recipe : allRecipes) {
//                if (recipe.getUser().getId() == userId) {
                    results.add(recipe);
//                }
            }
            return results;
        }

        if(fieldType.equals("all")) {
            results = findByValue(allRecipes, searchTerm);
            return results;
        }

        for (Recipe recipe : allRecipes) {
            String fieldValue = fieldMatch(recipe, fieldType);
            if (fieldValue != ""
                    && fieldValue.toLowerCase().contains(searchTerm)
//                    && recipe.getUser().getId() == userId
                ) {
                results.add(recipe);
            }
        }
        return results;
    }

    /**
     * Search all recipes and all fields for the search term.
     * @param allRecipes list of all recipes to search.
     * @param value the searchTerm.
     * @return a list of recipes where the searchTerm is in one of the fields.
     */

    public static List<Recipe> findByValue(Iterable<Recipe> allRecipes, String value) {

        List<Recipe> results = new ArrayList<>();

        for (Recipe recipe : allRecipes) {
//            if (recipe.getUser().getId() == userId) {
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

    /**
     * Search for value in the matching fieldTypes in Recipe class.
     * @param recipe the recipe to search.
     * @param fieldType the field being searched for.
     * @return the value in the matching field.
     */
    public static String fieldMatch (Recipe recipe, String fieldType) {
        String value;

        if (fieldType.equals("name")) {
            value = recipe.getName();
        } else if (fieldType.equals("description")) {
            value = recipe.getDescription();
        } else if (fieldType.equals("category")) {
            value = recipe.getCategories().toString();
        } else if (fieldType.equals("ingredient")) {
            value = recipe.getIngredients().toString();
        } else if (fieldType.equals("directions")) {
            value = recipe.getDirections().toString();
        }
//        else if (fieldType.equals("servings")) {
//            value = recipe.getServings().toString();
//        }
        else {
            value = "";
        }

        return value;
    }
}

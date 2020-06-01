package org.launchcode.recipestorage.controllers;

import org.launchcode.recipestorage.models.Recipe;
import org.launchcode.recipestorage.models.Search;
import org.launchcode.recipestorage.models.data.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private RecipeRepository recipeRepository;

    static Map<String, String> fieldTypes = new HashMap<>();

    public HomeController () {
        fieldTypes.put("all", "All");
        fieldTypes.put("name","Name");
        fieldTypes.put("description", "Description");
        fieldTypes.put("category", "Category");
        fieldTypes.put("ingredient", "Ingredient");
        fieldTypes.put("directions", "Directions");
        fieldTypes.put("servings", "Servings");
    }

    /**
     * Pull the home page of recipe storage.
     * @param session
     * @param model
     * @return
     */
    @GetMapping
    public String displaySearch(HttpSession session, Model model) {
        model.addAttribute("title", "Home");
        model.addAttribute("fieldTypes", fieldTypes);
        return "index";
    }

    /**
     * Submits search for recipe
     * @param model
     * @param searchTerm user input value to be searched.
     * @param fieldType field in which to search for the searchTerm.
     * @return a list of search results.
     */
    @PostMapping
    public String processSearch(Model model, HttpSession session, @RequestParam String searchTerm, @RequestParam String fieldType) {
        model.addAttribute("title", "Home");
        model.addAttribute("fieldTypes", fieldTypes);

        Iterable<Recipe> recipes;
        recipes = Search.find(recipeRepository.findAll(), searchTerm, fieldType);
        model.addAttribute("recipes", recipes);
        return "index";
    }


}

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

@Controller
public class HomeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping
    public String displaySearch(HttpSession session, Model model) {
        model.addAttribute("title", "Home");
        return "index";
    }

    @PostMapping
    public String processSearch(Model model, @RequestParam String searchTerm) {
        model.addAttribute("title", "Home");
        Iterable<Recipe> recipes;
        recipes = Search.findByValue(recipeRepository.findAll(), searchTerm);
        model.addAttribute("recipes", recipes);
        return "index";
    }

}

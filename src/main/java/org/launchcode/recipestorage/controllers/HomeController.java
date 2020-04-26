package org.launchcode.recipestorage.controllers;

import org.launchcode.recipestorage.models.Recipe;
import org.launchcode.recipestorage.models.Search;
import org.launchcode.recipestorage.models.data.RecipeRepository;
import org.launchcode.recipestorage.models.dto.LoginFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping
    public String displaySearch(LoginFormDTO loginFormDTO, Model model) {
        model.addAttribute("title", loginFormDTO.getUsername());
        return "index";
    }

    @PostMapping
    public String processSearch(Model model, @RequestParam String searchTerm) {
        Iterable<Recipe> recipes;
        recipes = Search.findValue(recipeRepository.findAll(), searchTerm);
//        model.addAttribute("title", loginFormDTO.getUsername());
        model.addAttribute("recipes", recipes);
        return "index";
    }

}

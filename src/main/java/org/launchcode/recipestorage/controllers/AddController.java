package org.launchcode.recipestorage.controllers;

import org.launchcode.recipestorage.models.data.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/add")
    public String displayAdd(Model model) {
        model.addAttribute("title", "Add Recipe");
        return "/add";
    }

    @PostMapping("/add")
    public String processAdd(Model model) {
        return "redirect:";
    }
}

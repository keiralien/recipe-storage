package org.launchcode.recipestorage.controllers;

import org.launchcode.recipestorage.models.Category;
import org.launchcode.recipestorage.models.Recipe;
import org.launchcode.recipestorage.models.data.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping
public class AddController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("Add")
    public String displayAddRecipe(Model model) {
        model.addAttribute("title", "Add Recipe");
        model.addAttribute(new Recipe());
        return "add";
    }

    @PostMapping("Add")
    public String processAddRecipe(@ModelAttribute @Valid Recipe newRecipe, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Recipe");
            model.addAttribute(new Recipe());
            return "add";
        }


        return "redirect:";
    }

}

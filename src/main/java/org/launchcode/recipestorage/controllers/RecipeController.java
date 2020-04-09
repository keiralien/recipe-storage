package org.launchcode.recipestorage.controllers;

import org.launchcode.recipestorage.models.Directions;
import org.launchcode.recipestorage.models.Ingredient;
import org.launchcode.recipestorage.models.Recipe;
import org.launchcode.recipestorage.models.Unit;
import org.launchcode.recipestorage.models.data.*;
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
@RequestMapping("recipe")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DirectionsRepository directionsRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private UnitRepository unitRepository;

    @GetMapping("/add")
    public String displayAddRecipe(Model model) {
        model.addAttribute("title", "Add Recipe");
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("units", unitRepository.findAll());
        model.addAttribute(new Recipe());
        model.addAttribute(new Directions());
        model.addAttribute(new Ingredient());
        return "recipe/add";
    }

    @PostMapping("/add")
    public String processAddRecipe(@ModelAttribute @Valid Recipe newRecipe, @ModelAttribute Directions newDirections,
                                   @ModelAttribute Ingredient newIngredient, @ModelAttribute Unit newUnit,
                                   Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Recipe");
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("units", unitRepository.findAll());
            model.addAttribute(new Recipe());
            model.addAttribute(new Directions());
            model.addAttribute(new Ingredient());
            return "recipe/add";
        }

        recipeRepository.save(newRecipe);
        directionsRepository.save(newDirections);
        ingredientRepository.save(newIngredient);
        return "redirect:";
    }

}

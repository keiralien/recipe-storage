package org.launchcode.recipestorage.controllers;

import org.launchcode.recipestorage.models.*;
import org.launchcode.recipestorage.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
                                   @ModelAttribute Ingredient newIngredient, @RequestParam List<Integer> categories,
                                   @RequestParam Integer unitId, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Recipe");
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("units", unitRepository.findAll());
            model.addAttribute(new Recipe());
            model.addAttribute(new Directions());
            model.addAttribute(new Ingredient());
            return "recipe/add";
        }

//        Optional<Employer> empVar = employerRepository.findById(employerId);
//        Employer employer = empVar.get();
//        newJob.setEmployer(employer);

        List<Category> categoryObj = (List<Category>) categoryRepository.findAllById(categories);
        newRecipe.setCategories(categoryObj);

        Optional<Unit> unitObj = unitRepository.findById(unitId);
        Unit unit = unitObj.get();
        newIngredient.setUnit(unit);

        recipeRepository.save(newRecipe);
        ingredientRepository.save(newIngredient);
        directionsRepository.save(newDirections);

        return "/";
    }

    @GetMapping("/browse")
    public String recipeBrowse (Model model) {
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipe/browse";
    }

    @GetMapping("/view/{recipeId}")
    public String recipeView (Model model, @PathVariable int recipeId) {
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        if (optRecipe.isPresent()) {
            Recipe recipe = (Recipe) optRecipe.get();
            model.addAttribute("recipe", recipe);
            return "recipe/view";
        } else {
            return "redirect:../";
        }
    }
}

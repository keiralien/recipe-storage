package org.launchcode.recipestorage.controllers;

import org.launchcode.recipestorage.models.*;
import org.launchcode.recipestorage.models.data.*;
import org.launchcode.recipestorage.models.dto.DirectionsDTO;
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
//        DirectionsDTO directionsList = new DirectionsDTO();
//        directionsList.addDirections(new Directions());

        model.addAttribute("title", "Add Recipe");
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("units", unitRepository.findAll());
//        model.addAttribute("directions", directionsList);
        model.addAttribute(new Recipe());
        model.addAttribute(new Directions());
        model.addAttribute(new Ingredient());
        return "recipe/add";
    }

    @PostMapping("/add")
    public String processAddRecipe(@ModelAttribute @Valid Recipe newRecipe,
//                                   @ModelAttribute("Directions") DirectionsDTO directionsList,
                                   @ModelAttribute @Valid Directions newDirections,
                                   @ModelAttribute @Valid Ingredient newIngredient,
                                   @RequestParam List<Integer> categories,
                                   Integer unitId, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Recipe");
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("units", unitRepository.findAll());
//            model.addAttribute("directions", directionsList);
            model.addAttribute(new Recipe());
            model.addAttribute(new Directions());
            model.addAttribute(new Ingredient());
            return "/recipe/add";
        }

        List<Category> categoryObj = (List<Category>) categoryRepository.findAllById(categories);
        newRecipe.setCategories(categoryObj);

        Optional<Unit> unitObj = unitRepository.findById(unitId);
        Unit unit = unitObj.get();
        newIngredient.setUnit(unit);

        recipeRepository.save(newRecipe);

        Optional<Recipe> recObj = recipeRepository.findById(newRecipe.getId());
        Recipe recipe = recObj.get();

        newDirections.setRecipe(recipe);
        directionsRepository.save(newDirections);

        newIngredient.setRecipe(recipe);
        ingredientRepository.save(newIngredient);
//
//        for (Ingredient ingredient : ingredientList) {
//
//            Optional<Unit> unitObj = unitRepository.findById(unitId);
//            Unit unit = unitObj.get();
//            ingredient.setUnit(unit);
//
//            Optional<Recipe> recObj = recipeRepository.findById(newRecipe.getId());
//            Recipe recipe = recObj.get();
//            ingredient.setRecipe(recipe);
//
//            ingredientRepository.save(ingredient);
//        }
//
//        for (Directions direction : directionsList.getDirectionsList()) {
//            Optional<Recipe> recObj = recipeRepository.findById(newRecipe.getId());
//            Recipe recipe = recObj.get();
//            direction.setRecipe(recipe);
//
//            directionsRepository.save(direction);
//        }

        return "recipe/browse";
    }

    @GetMapping("/browse")
    public String displayRecipeBrowse (Model model) {
        model.addAttribute("title", "Browse Recipes");
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipe/browse";
    }

    @GetMapping("/view/{recipeId}")
    public String displayRecipeView (Model model, @PathVariable int recipeId) {
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        if (optRecipe.isPresent()) {
            Recipe recipe = (Recipe) optRecipe.get();
            model.addAttribute("recipe", recipe);
            model.addAttribute("title",recipe.getName());
            return "recipe/view";
        } else {
            return "redirect:../";
        }
    }

    @GetMapping("/edit/{recipeId}")
    public String displayRecipeEdit (Model model, @PathVariable int recipeId) {
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        if (optRecipe.isPresent()) {
            Recipe recipe = (Recipe) optRecipe.get();
            model.addAttribute("recipe", recipe);
            model.addAttribute("title", "Edit " + recipe.getName());
            return "recipe/edit";
        } else{
            return "redirect:../";
        }
    }

    @PostMapping("/edit/{recipeId}")
    public String processRecipeEdit () {
        return "/recipe/browse";
    }
}

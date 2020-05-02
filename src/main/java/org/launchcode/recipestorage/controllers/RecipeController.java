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
        model.addAttribute("title", "Add Recipe");
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("units", unitRepository.findAll());
        model.addAttribute(new Recipe());
        model.addAttribute(new Directions());
        model.addAttribute(new Ingredient());
        return "recipe/add";
    }

    @PostMapping("/add")
    public String processAddRecipe(@ModelAttribute @Valid Recipe newRecipe,
                                   @ModelAttribute @Valid Directions newDirections,
                                   @ModelAttribute @Valid Ingredient newIngredient,
                                   @RequestParam String recName,
                                   @RequestParam List<Integer> categories,
                                   Integer unitId, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Recipe");
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("units", unitRepository.findAll());
            model.addAttribute(new Recipe());
            model.addAttribute(new Directions());
            model.addAttribute(new Ingredient());
            return "/recipe/add";
        }

        List<Category> categoryObj = (List<Category>) categoryRepository.findAllById(categories);
        newRecipe.setCategories(categoryObj);

        newRecipe.setName(recName);
        recipeRepository.save(newRecipe);

        Optional<Unit> unitObj = unitRepository.findById(unitId);
        Unit unit = unitObj.get();
        newIngredient.setUnit(unit);

        Optional<Recipe> recObj = recipeRepository.findById(newRecipe.getId());
        Recipe recipe = recObj.get();

        newDirections.setRecipe(recipe);
        directionsRepository.save(newDirections);

        newIngredient.setRecipe(recipe);
        ingredientRepository.save(newIngredient);

        return "/recipe/browse";
    }

    @GetMapping("/browse")
    public String displayRecipeBrowse (Model model) {
        model.addAttribute("title", "Browse Recipes");
        model.addAttribute("recipes", recipeRepository.findAll());
        return "/recipe/browse";
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
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("units", unitRepository.findAll());

        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);

        if (optRecipe.isPresent()) {
            Recipe recipe = (Recipe) optRecipe.get();
            model.addAttribute("recipe", recipe);
            model.addAttribute("title", "Edit: " + recipe.getName());
//            model.addAttribute("directions", recipe.getDirections());
            model.addAttribute("ingredients", recipe.getIngredients());
            return "recipe/edit";
        } else{
            return "redirect:../";
        }
    }

    @PostMapping("/edit/{eventId}")
    public String processRecipeEdit (@ModelAttribute @Valid Recipe recipe,
                                     @ModelAttribute @Valid List<Ingredient> ingredients,
//                                     @ModelAttribute @Valid List<Directions> directions,
                                     @RequestParam String recName,
                                     @RequestParam int eventId, Model model, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit " + recipe.getName());
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("units", unitRepository.findAll());
            return "/recipe/edit";
        }
        Optional<Recipe> optRecipe = recipeRepository.findById(eventId);
        if (optRecipe.isPresent()) {
            Recipe recipeHolder = optRecipe.get();

            recipeHolder.setName(recName);
            recipeHolder.setDescription(recipe.getDescription());
            recipeHolder.setCategories(recipe.getCategories());
            recipeHolder.setIngredients(ingredients);
            recipeHolder.setDirections(recipe.getDirections());

            recipeRepository.save(recipeHolder);
        }

        return "/recipe/browse";
    }

    @GetMapping("/delete")
    public String displayDeleteRecipe (Model model) {
        model.addAttribute("title", "Delete Recipes");
        model.addAttribute("recipes", recipeRepository.findAll());
        return "/delete";
    }

    @PostMapping("delete")
    public String processDeleteRecipe(@RequestParam(required = false) int[] recipeIds) {
        if(recipeIds != null) {
            for (int id : recipeIds) {
                recipeRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

}

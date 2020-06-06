package org.launchcode.recipestorage.controllers;

import org.launchcode.recipestorage.models.*;
import org.launchcode.recipestorage.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
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

    private List<String> directionsList = new ArrayList<>();

    private static final String ajaxHeaderName = "X-Requested-With";
    private static final String ajaxHeaderValue = "XMLHttpRequest";

//    Return the Add Recipe page passing in page title, existing category and unit information,
//    and the models for mapping new records.
    @GetMapping("/add")
    public String displayAddRecipe(Model model) {
        directionsList.add("");
        model.addAttribute("title", "Add Recipe");
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("units", unitRepository.findAll());
        model.addAttribute("directionsList", directionsList);
        model.addAttribute(new Recipe());
        model.addAttribute(new Directions());
        model.addAttribute(new Ingredient());
        return "recipe/add";
    }

    @PostMapping(params = "addDirection", path = {"/add"})
    public String addDirection(HttpServletRequest request) {
        if(ajaxHeaderValue.equals(request.getHeader(ajaxHeaderName))) {
            directionsList.add("");
            return "recipe/add::#directionsSection";
        } else {
            return "recipe/add";
        }
    }

//    Process the addition of a new recipe and all related information.
    @PostMapping("/add")
    public String processAddRecipe(@ModelAttribute @Valid Recipe newRecipe,
                                   @ModelAttribute @Valid Directions newDirections,
                                   @ModelAttribute @Valid Ingredient newIngredient,
                                   @RequestParam List<Integer> categories,
                                   @RequestParam List<String> ingredientNameList,
                                   @RequestParam List<Double> ingredientAmountList,
                                   @RequestParam List<Integer> unitIdList,
                                   @RequestParam List<String> directionsList,
                                   Errors errors, Model model) {

//        Check for errors in the new recipe and return those errors.
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Recipe");
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("units", unitRepository.findAll());
            model.addAttribute(new Recipe());
            model.addAttribute(new Directions());
            model.addAttribute(new Ingredient());
            return "/recipe/add";
        }

//        Check whether the recipe name entered matches an existing recipe name. If so,
//        return with a message indicating recipe name is a duplicate.
        if(recipeRepository.findAll().toString().toLowerCase().equals(newRecipe.getName().toLowerCase())) {
            model.addAttribute("message", "A recipe with that name already exists.");
            model.addAttribute("title", "Add Recipe");
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("units", unitRepository.findAll());
            model.addAttribute(new Recipe());
            model.addAttribute(new Directions());
            model.addAttribute(new Ingredient());
            return "/recipe/add";
        }

//        If recipe name is not a duplicate, save the new recipe.
        List<Category> categoryObj = (List<Category>) categoryRepository.findAllById(categories);
        newRecipe.setCategories(categoryObj);

        recipeRepository.save(newRecipe);

        Optional<Recipe> recObj = recipeRepository.findById(newRecipe.getId());
        Recipe recipe = recObj.get();

//        Create direction objects related to this recipe

//        String[] dirList = directionsList.toString().split(",");

        for (String instruction : directionsList) {
            newDirections.setRecipe(recipe);
            newDirections.setInstruction(instruction);
            directionsRepository.save(newDirections);
        }

        for (int i = 0; i < ingredientNameList.size(); i++) {
            newIngredient.setRecipe(recipe);
            newIngredient.setName(ingredientNameList.get(i));
            newIngredient.setAmount(ingredientAmountList.get(i));

            Optional<Unit> unitObj = unitRepository.findById(unitIdList.get(i));
            Unit unit = unitObj.get();
            newIngredient.setUnit(unit);

            ingredientRepository.save(newIngredient);
        }

        model.addAttribute("recipes", recipeRepository.findAll());
        return "/recipe/browse";
    }

//  View a list of all recipes
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
            return "recipe/edit";
        } else{
            return "redirect:../";
        }
    }

    @PostMapping("/edit/{recipeId}")
    public String processRecipeEdit (@ModelAttribute @Valid Recipe recipe,
                                     @ModelAttribute @Valid Directions directions,
                                     @ModelAttribute @Valid Ingredient ingredient,
                                     @RequestParam int recipeId,
                                     @RequestParam int[] directionIds,
                                     @RequestParam int[] ingredientIds,
                                     Model model, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit " + recipe.getName());
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("units", unitRepository.findAll());
            return "/recipe/edit";
        }
        List<Directions> directionsList = new ArrayList<>();
        List<Ingredient> ingredientsList = new ArrayList<>();

        Optional<Recipe> origRecipe = recipeRepository.findById(recipeId);
        if (origRecipe.isPresent()) {
            Recipe recipeHolder = origRecipe.get();

            for (int i = 0; i < ingredientIds.length; i++) {
                Optional<Ingredient> origIngredient = ingredientRepository.findById(ingredientIds[i]);
                Ingredient ingredientHolder = origIngredient.get();

                ingredientHolder.setRecipe(recipeHolder);
                ingredientHolder.setName(recipe.getIngredients().get(i).getName());
                ingredientHolder.setAmount(recipe.getIngredients().get(i).getAmount());
                ingredientHolder.setUnit(recipe.getIngredients().get(i).getUnit());

                ingredientsList.add(ingredientHolder);
            }

            for (int i = 0; i < directionIds.length; i++) {
                Optional<Directions> origDirections = directionsRepository.findById(directionIds[i]);
                Directions directionsHolder = origDirections.get();

                directionsHolder.setRecipe(recipeHolder);
                directionsHolder.setInstruction(recipe.getDirections().get(i).getInstruction());

                directionsList.add(directionsHolder);
            }

            recipeHolder.setName(recipe.getName());
            recipeHolder.setDescription(recipe.getDescription());
            recipeHolder.setCategories(recipe.getCategories());
            recipeHolder.setIngredients(ingredientsList);
            recipeHolder.setDirections(directionsList);

            model.addAttribute("title", "Browse Recipes");
            model.addAttribute("recipes", recipeRepository.findAll());
            recipeRepository.save(recipeHolder);
        }
        return "/recipe/browse";
    }

    @GetMapping("/delete")
    public String displayDeleteRecipe (Model model) {
        model.addAttribute("title", "Delete Recipes");
        model.addAttribute("recipes", recipeRepository.findAll());
        return "/recipe/delete";
    }

    @PostMapping("/delete")
    public String processDeleteRecipe(@RequestParam(required = false) int[] recipeIds, Model model) {
        if(recipeIds != null) {
            for (int i = 0; i < recipeIds.length; i++) {
                Optional<Recipe> origRecipe = recipeRepository.findById(recipeIds[i]);
                Recipe recipe = origRecipe.get();

                for (Ingredient ingredient : recipe.getIngredients()) {
                    ingredientRepository.deleteById(ingredient.getId());
                }
                for (Directions direction : recipe.getDirections()) {
                    directionsRepository.deleteById(direction.getId());
                }
                recipeRepository.deleteById(recipeIds[i]);
            }
        }
        model.addAttribute("title", "Browse Recipes");
        model.addAttribute("recipes", recipeRepository.findAll());
        return "/recipe/browse";
    }

}

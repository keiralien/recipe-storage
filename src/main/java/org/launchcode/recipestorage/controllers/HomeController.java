package org.launchcode.recipestorage.controllers;

import org.launchcode.recipestorage.models.Recipe;
import org.launchcode.recipestorage.models.Search;
import org.launchcode.recipestorage.models.data.CategoryRepository;
import org.launchcode.recipestorage.models.data.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    private CategoryRepository categoryRepository;


    public Recipe randomRecipe() {
        Long qty = recipeRepository.count();
        int idx = (int)(Math.random() * qty);
        Page<Recipe> recipePage = recipeRepository.findAll(PageRequest.of(idx, 1));
        Recipe recipe = null;
        if (recipePage.hasContent()) {
            recipe = recipePage.getContent().get(0);
        }
        return recipe;
    }

    /**
     * Pull the home page of recipe storage.
     * @param session
     * @param model
     * @return
     */
    @GetMapping
    public String displayHome(HttpSession session, Model model) {
        model.addAttribute("title", "Home");
        model.addAttribute("categories",categoryRepository.findAll());
        model.addAttribute("recipe",randomRecipe());
        return "index";
    }

    /**
     * Submits search for recipe
     * @param model
     * @param searchTerm user input value to be searched.
//     * @param fieldType field in which to search for the searchTerm.
     * @return a list of search results.
     */
    @PostMapping
    public String processSearch(Model model, HttpSession session,
                                @RequestParam (required = false) String searchTerm,
                                @RequestParam (required = false) String categoryId) {

        Iterable<Recipe> recipes;
        recipes = Search.find(recipeRepository.findAll(), searchTerm);
        model.addAttribute("title", "Home");
        model.addAttribute("categories",categoryRepository.findAll());
        model.addAttribute("recipes", recipes);
        return "index";
    }
}

package org.launchcode.recipestorage.controllers;

import org.launchcode.recipestorage.models.Category;
import org.launchcode.recipestorage.models.Recipe;
import org.launchcode.recipestorage.models.data.CategoryRepository;
import org.launchcode.recipestorage.models.data.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    private void deleteCategory (int[] categoryIds) {
        for (int i = 0; i < categoryIds.length; i++) {
            Optional<Category> origCategory = categoryRepository.findById(categoryIds[i]);
            Category category = origCategory.get();

            Iterable<Recipe> recipeList = recipeRepository.findAll();
            for (Recipe recipe : recipeList) {
                if(recipe.getCategories().contains(category)) {
                    List<Category> updatedCategories = recipe.getCategories();
                    updatedCategories.remove(category);
                    recipe.setCategories(updatedCategories);
                    recipeRepository.save(recipe);
                }
            }

            categoryRepository.deleteById(categoryIds[i]);
        }
    }

    @GetMapping("/add")
    public String displayAddCategory (Model model) {
        model.addAttribute("title", "Add or Remove Category");
        model.addAttribute("categories",categoryRepository.findAll());
        model.addAttribute(new Category());
        return "category/add";
    }

    @PostMapping("/add")
    public String processAddCategory (@ModelAttribute @Valid Category newCategory, Model model, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Category");
            model.addAttribute(new Category());
            return "/category/add";
        }
        if(categoryRepository.findAll().toString().toLowerCase().contains(newCategory.getName().toLowerCase())) {
            model.addAttribute("message", "Category already exists.");
            model.addAttribute("title", "Add or Remove Category");
            model.addAttribute("categories",categoryRepository.findAll());
            model.addAttribute(new Category());
            return"/category/add";
        }
        categoryRepository.save(newCategory);
        model.addAttribute("title","My Categories");
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute(new Category());
        return "/category/add";
    }

    @GetMapping("/delete")
    public String displayDeleteRecipe (Model model) {
        model.addAttribute("title", "Delete Category");
        model.addAttribute("categories", categoryRepository.findAll());
        return "/category/delete";
    }

    @PostMapping("/delete")
    public String processDeleteRecipe(@RequestParam(required = false) int[] categoryIds,
                                      Model model) {
        if(categoryIds != null) {
            deleteCategory(categoryIds);
        }

        model.addAttribute("title", "Add or Remove Category");
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute(new Category());
        return "category/add";
    }
}

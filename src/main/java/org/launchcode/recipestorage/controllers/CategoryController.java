package org.launchcode.recipestorage.controllers;

import org.launchcode.recipestorage.models.Category;
import org.launchcode.recipestorage.models.data.CategoryRepository;
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
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String displayAddCategory(Model model) {
        model.addAttribute("title", "Add or Remove Category");
        model.addAttribute("categories",categoryRepository.findAll());
        model.addAttribute(new Category());
        return "category";
    }

    @PostMapping
    public String processAddCategory(@ModelAttribute @Valid Category newCategory, Model model, Errors errors) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Category");
            model.addAttribute(new Category());
            return "category";
        }
        categoryRepository.save(newCategory);
        return "category";
    }

    @GetMapping("/browse")
    public String recipeBrowse (Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "category/browse";
    }

}

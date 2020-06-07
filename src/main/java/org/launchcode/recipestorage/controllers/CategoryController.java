package org.launchcode.recipestorage.controllers;

import org.launchcode.recipestorage.models.Category;
import org.launchcode.recipestorage.models.data.CategoryRepository;
import org.launchcode.recipestorage.models.dto.LoginFormDTO;
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
        return "/category/browse";
    }

    @GetMapping("/browse")
    public String recipeBrowse (LoginFormDTO loginFormDTO, Model model) {
        model.addAttribute("title","My Categories");
        model.addAttribute("categories", categoryRepository.findAll());
        return "/category/browse";
    }

}

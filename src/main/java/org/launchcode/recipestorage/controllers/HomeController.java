package org.launchcode.recipestorage.controllers;

import org.launchcode.recipestorage.models.dto.LoginFormDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String index(LoginFormDTO loginFormDTO, Model model) {
        model.addAttribute("title", loginFormDTO.getUsername());
        return "index";
    }

}

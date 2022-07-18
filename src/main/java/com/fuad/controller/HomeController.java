package com.fuad.controller;

import com.fuad.dto.StatusDto;
import com.fuad.entity.User;
import com.fuad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String root() {
        return "redirect:/feed";
    }

    @GetMapping("/feed")
    public String feed(Model model) {

        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("status", new StatusDto());
        return "index";
    }

    @GetMapping("/home")
    public String home(){

        return "home";
    }
}


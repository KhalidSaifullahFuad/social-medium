package com.fuad.controller;

import com.fuad.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping()
    public String root() {
        return "redirect:/feed";
    }

    @GetMapping("/feed")
    public String feed(){
        return "index";
    }

    @GetMapping("/home")
    public String home(){

        return "home";
    }
}


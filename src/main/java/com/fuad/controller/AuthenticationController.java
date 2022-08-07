package com.fuad.controller;

import com.fuad.dao.LocationDAO;
import com.fuad.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthenticationController extends BaseController {


    @GetMapping("/login")
    public String login(Model model) {

        if(userDAO.findAll().size() == 0){
            createDemoUser();
            model.addAttribute("demoUser", "admin");
        }

        return "auth/login";
    }

    @GetMapping("/logout")
    public String logout(){

        return "auth/logout";
    }
}

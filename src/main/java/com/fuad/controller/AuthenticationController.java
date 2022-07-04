package com.fuad.controller;

import com.fuad.dao.UserDAO;
import com.fuad.entity.User;
import com.fuad.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    private final UserDAO userDAO;

    public AuthenticationController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/login")
    public String login(){
        var user = new User();
        user.setName("Admin");
        user.setEmail("admin@gmail.com");
        user.setPassword("secret");
        user.setRole(Role.ROLE_ADMIN);
        userDAO.insert(user);
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logout(){

        return "auth/logout";
    }
}

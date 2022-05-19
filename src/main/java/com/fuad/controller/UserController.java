package com.fuad.controller;

import com.fuad.dao.UserDAO;
import com.fuad.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/create")
    public ModelAndView create(Model model) {

        model.addAttribute("user", new User());

        return new ModelAndView("user/create", "model", model);
    }

    @PostMapping("/store")
    public String store(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("user", user);

        user.setId(System.currentTimeMillis());
        userDAO.insert(user);

        return "user/show";
    }

    @PostMapping("/show/{id}")
    public String store(Model model, @PathVariable("id") Long id) {
        User user = userDAO.getById(id);
        model.addAttribute("user", user);
        return "user/show";
    }

    @PostMapping("/update")
    public String update(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("user", user);

        userDAO.update(user);

        return "user/show";
    }

    @PostMapping("/delete")
    public String update(Model model, @PathVariable("id") Long id) {



        return "user/show";
    }


//    @GetMapping("/maintain")
//    public String maintain(Model model) {
//
//        List<User> userList = userDAO.getAll();
//        model.addAttribute("userList", userList);
//
//        return "user/maintain";
//    }
}


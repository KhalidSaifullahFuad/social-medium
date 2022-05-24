package com.fuad.controller;

import com.fuad.dao.LocationDAO;
import com.fuad.dao.UserDAO;
import com.fuad.dto.UserDto;
import com.fuad.model.Location;
import com.fuad.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private LocationDAO locationDAO;

    @GetMapping("/create")
    public ModelAndView create(Model model) {

        List<Location> locations = locationDAO.getAll();
        List<String> locationList = new ArrayList<>();

        for (Location location : locations) {
            locationList.add(location.getLocationName());
        }

        model.addAttribute("locationList", locationList);
        model.addAttribute("userDto", new UserDto());

        return new ModelAndView("user/create", "model", model);
    }

    @PostMapping("/store")
    public String store(Model model, @ModelAttribute("userDto") UserDto userDto) {

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
//        user.setLocation(locationDAO.getByName(userDto.getLocation()));

        System.out.println("Name: "+userDto.getName());
        System.out.println("Email: "+userDto.getEmail());
        System.out.println("Password: "+userDto.getPassword());
            System.out.println("Location: "+userDto.getLocation());
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


    @GetMapping("/maintain")
    public String maintain(Model model) {

        List<User> userList = userDAO.getAll();
        model.addAttribute("userList", userList);

        return "user/maintain";
    }
}


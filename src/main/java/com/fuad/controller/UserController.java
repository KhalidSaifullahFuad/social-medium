package com.fuad.controller;

import com.fuad.config.Utils;
import com.fuad.dao.LocationDAO;
import com.fuad.dao.UserDAO;
import com.fuad.entity.Attachment;
import com.fuad.model.UserDto;
import com.fuad.entity.Location;
import com.fuad.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping(value = "/store")
    public String store(Model model, @ModelAttribute("userDto") UserDto userDto, @RequestPart("image") MultipartFile file) {

        Location location = locationDAO.getByName(userDto.getLocation());

        Attachment attachment = Utils.saveFile(file, 11L);

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setLocation(location);
        user.setAttachment(attachment);
        userDAO.insert(user);

        location.getUsers().add(user);
        locationDAO.update(location);


        model.addAttribute("user", user);

        return "user/show";
    }

    @GetMapping(value = "/show/{id}")
    public String show(Model model, @PathVariable(value = "id") String id) {

        User user = userDAO.getById(Long.parseLong(id));

        model.addAttribute("user", user);

        return "redirect:/user/show";
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


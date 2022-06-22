package com.fuad.controller;

import com.fuad.config.Properties;
import com.fuad.config.FileUtils;
import com.fuad.dao.LocationDAO;
import com.fuad.dao.UserDAO;
import com.fuad.dto.UserResponseDto;
import com.fuad.entity.Attachment;
import com.fuad.dto.UserDto;
import com.fuad.entity.Location;
import com.fuad.entity.User;


import com.fuad.enums.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private LocationDAO locationDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/create")
    public ModelAndView create(Model model) {

        List<Location> locations = locationDAO.getAll();
        List<String> locationList = new ArrayList<>();

        for (Location location : locations) {
            locationList.add(location.getLocationName());
        }

        System.out.println(">>>>> "+passwordEncoder.encode("123"));

        model.addAttribute("locationList", locationList);
        model.addAttribute("userDto", new UserDto());

        return new ModelAndView("user/create", "model", model);
    }

    @PostMapping(value = "/store")
    @Validated
    public String store(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult br, @RequestParam("image") MultipartFile file) throws IOException {

        if (br.hasFieldErrors()) {
            return "user/create";
        }
        Location location = locationDAO.getByName(userDto.getLocation());
        Attachment attachment = FileUtils.saveFile(file, Properties.USER_FOLDER);

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.USER);
        user.setLocation(location);
        user.setAttachment(attachment);

        userDAO.insert(user);

        return "redirect:/user/show/" + user.getId();
    }

    @GetMapping(value = "/show/{id}")
    public String show(Model model, @PathVariable(value = "id") String id) {

        User user = userDAO.getById(Long.parseLong(id));
        UserResponseDto userResponseDto = new UserResponseDto();

        BeanUtils.copyProperties(user, userResponseDto);

        byte[] bytes = FileUtils.getFile(user.getAttachment().getAttachmentPath());
        String imgUrl = Base64.getEncoder().encodeToString(bytes);

        userResponseDto.setLocationName(user.getLocation().getLocationName());
        userResponseDto.setImage(imgUrl);

        model.addAttribute("user", userResponseDto);

        return "user/show";
    }

    @PostMapping("/update")
    public String update(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("user", user);

        userDAO.update(user);

        return "user/show";
    }

    @GetMapping("/maintain")
    public String maintain(Model model) {

        List<User> userList = userDAO.getAll();

        model.addAttribute("userList", userList);

        return "user/maintain";
    }
}


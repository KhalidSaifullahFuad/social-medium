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
@RequestMapping("/")
public class UserController extends BaseController{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("user/create")
    public String create(Model model) {

        model.addAttribute("locationList", getAllLocation());
        model.addAttribute("userDto", new UserDto());

        return "user/create";
    }

    @PostMapping(value = "user/store")
    @Validated
    public String store(Model model, @Valid @ModelAttribute("userDto") UserDto userDto, BindingResult br, @RequestParam("image") MultipartFile file) throws IOException {

        if (br.hasFieldErrors()) {
            model.addAttribute("locationList", getAllLocation());
            return "/user/create";
        }

        Location location = locationDAO.findByLocationName(userDto.getLocation());

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(Role.ROLE_USER);
        user.setLocation(location);

        if(!file.isEmpty()) {
            Attachment attachment = FileUtils.saveFile(file, Properties.USER_FOLDER);
            user.setAttachment(attachment);
        }

        userDAO.save(user);

        return "redirect:/user/show/" + user.getId();
    }

//    @GetMapping(value = "/show/{id}")
//    public String show(Model model, @PathVariable(value = "id") String id) {
//
//        User user = userDAO.getById(Long.parseLong(id));
//        UserResponseDto userResponseDto = new UserResponseDto();
//
//        BeanUtils.copyProperties(user, userResponseDto);
//
//        userResponseDto.setLocationName(user.getLocation().getLocationName());
//
//        if(user.getAttachment() != null) {
//            byte[] bytes = FileUtils.getFile(user.getAttachment().getAttachmentPath());
//            String imgUrl = Base64.getEncoder().encodeToString(bytes);
//            userResponseDto.setImage(imgUrl);
//        }
//        model.addAttribute("user", userResponseDto);
//
//        return "user/show";
//    }
//
//    @PostMapping("/update")
//    public String update(Model model, @ModelAttribute("user") User user) {
//        model.addAttribute("user", user);
//
//        userDAO.update(user);
//
//        return "user/show";
//    }

    @GetMapping("user/all")
    public String all(Model model) {

        List<User> userList = userDAO.findAll();
        model.addAttribute("userList", userList);

        return "user/peoples";
    }
}


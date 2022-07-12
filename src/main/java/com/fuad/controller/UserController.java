package com.fuad.controller;

import com.fuad.dto.UserDto;
import com.fuad.entity.User;
import com.fuad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

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

        Long id = userService.insert(userDto, file);

        return "redirect:/user/show/" + id;
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


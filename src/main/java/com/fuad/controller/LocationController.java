package com.fuad.controller;

import com.fuad.dao.LocationDAO;
import com.fuad.dto.LocationDto;
import com.fuad.entity.Location;
import com.fuad.entity.Status;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/location")
@Validated
public class LocationController {

    @Autowired
    private LocationDAO locationDAO;

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("location", new LocationDto());

        return "location/create";
    }

    @PostMapping("/store")
    public String store(@Valid @ModelAttribute("location") LocationDto locationDto, BindingResult result) {
        if (result.hasErrors())
            return "location/create";

        Location location = new Location();
        BeanUtils.copyProperties(locationDto, location);
        locationDAO.insert(location);

        return "redirect:location/show/" + location.getId();
    }

    @GetMapping(value = "/show/{id}")
    public String show(Model model, @PathVariable(value = "id") String id) {

        Location location = locationDAO.getById(Long.parseLong(id));
        model.addAttribute("location", location);

        return "location/show";
    }

    @GetMapping("/all")
    public String all(Model model) {

        List<Location> locationList = locationDAO.getAll();
        model.addAttribute("locationList", locationList);

        return "location/locations";
    }
}

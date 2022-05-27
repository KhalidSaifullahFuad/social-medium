package com.fuad.controller;

import com.fuad.dao.LocationDAO;
import com.fuad.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationDAO locationDAO;

    @GetMapping("/create")
    public ModelAndView create(Model model) {

        model.addAttribute("location", new Location());

        return new ModelAndView("location/create", "model", model);
    }

    @PostMapping("/store")
    public String store(Model model, @ModelAttribute("location") Location location) {
        locationDAO.insert(location);

        model.addAttribute("location", location);

        return "location/show";
    }

    @GetMapping("/list")
    public String maintain(Model model) {

        List<Location> locationList = locationDAO.getAll();
        model.addAttribute("locationList", locationList);

        return "location/list";
    }
}

package com.fuad.controller;

import com.fuad.dao.LocationDAO;
import com.fuad.dao.StatusDAO;
import com.fuad.entity.Location;
import com.fuad.entity.Status;
import com.fuad.model.StatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusDAO statusDAO;

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
        model.addAttribute("privacyList", Arrays.asList("Public", "Private"));
        model.addAttribute("status", new StatusModel());

        return new ModelAndView("status/create", "model", model);
    }

    @PostMapping("/store")
    public String store(Model model, @ModelAttribute("status") StatusModel statusModel) {

        Status status = new Status();
        status.setTitle(statusModel.getTitle());
        status.setDescription(statusModel.getDescription());
        status.setPrivacy(statusModel.getPrivacy());
        status.setLocation(locationDAO.getByName(statusModel.getLocation()));

        statusDAO.insert(status);

        model.addAttribute("status", status);

        return "status/show";
    }

    @GetMapping("/list")
    public String maintain(Model model) {

        List<Status> statusList = statusDAO.getAll();
        model.addAttribute("statusList", statusList);

        return "status/list";
    }
}

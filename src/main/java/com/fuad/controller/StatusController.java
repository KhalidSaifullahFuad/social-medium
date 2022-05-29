package com.fuad.controller;

import com.fuad.config.Utils;
import com.fuad.dao.AttachmentDAO;
import com.fuad.dao.LocationDAO;
import com.fuad.dao.StatusDAO;
import com.fuad.entity.Attachment;
import com.fuad.entity.Location;
import com.fuad.entity.Status;
import com.fuad.entity.User;
import com.fuad.model.StatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @Autowired
    private AttachmentDAO attachmentDAO;

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
    public String store(Model model, @ModelAttribute("status") StatusModel statusModel, @RequestParam("images") MultipartFile[] files) {

        Location location = locationDAO.getByName(statusModel.getLocation());
        List<Attachment> attachmentList = new ArrayList<>();

        for (MultipartFile file : files) {
            System.out.println("File name: " + file.getOriginalFilename());

            Attachment attachment = Utils.saveFile(file, file.getOriginalFilename());
            if (attachment != null) {
                attachmentList.add(attachment);
            }
        }

        attachmentDAO.insertBulks(attachmentList);

        Status status = new Status();
        status.setTitle(statusModel.getTitle());
        status.setDescription(statusModel.getDescription());
        status.setPrivacy(statusModel.getPrivacy());
        status.setLocation(location);
        status.setStatusAttachmentList(attachmentList);

        statusDAO.insert(status);

        location.getStatuses().add(status);
        locationDAO.update(location);

        model.addAttribute("status", status);

        return "redirect:/status/show/" + status.getId();
    }

    @GetMapping(value = "/show/{id}")
    public String show(Model model, @PathVariable(value = "id") String id) {

        Status status = statusDAO.getById(Long.parseLong(id));

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

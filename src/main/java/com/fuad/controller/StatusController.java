package com.fuad.controller;

import com.fuad.config.Properties;
import com.fuad.config.FileUtils;
import com.fuad.dao.AttachmentDAO;
import com.fuad.dao.LocationDAO;
import com.fuad.dao.StatusDAO;
import com.fuad.entity.Attachment;
import com.fuad.entity.Location;
import com.fuad.entity.Status;
import com.fuad.dto.StatusDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
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
        model.addAttribute("status", new StatusDto());

        return new ModelAndView("status/create", "model", model);
    }

    @PostMapping("/store")
    public String store(Model model, @ModelAttribute("status") StatusDto statusModel, @RequestParam("images") MultipartFile[] files) throws IOException {

        Location location = locationDAO.getByName(statusModel.getLocation());
        List<Attachment> attachmentList = new ArrayList<>();

        for (MultipartFile file : files) {
            Attachment attachment = FileUtils.saveFile(file, Properties.STATUS_FOLDER);
            if (attachment != null) {
                attachmentList.add(attachment);
            }
        }

        attachmentDAO.insertBulk(attachmentList);

        Status status = new Status();
        BeanUtils.copyProperties(statusModel, status);
        status.setLocation(location);
        status.setStatusAttachmentList(attachmentList);

        statusDAO.insert(status);

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

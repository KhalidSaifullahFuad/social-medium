package com.fuad.controller;

import com.fuad.config.Properties;
import com.fuad.service.UserService;
import com.fuad.util.FileUtils;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/status")
@Validated
public class StatusController extends BaseController {

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    private AttachmentDAO attachmentDAO;

    @Autowired
    private UserService userService;

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("locationList", getAllLocation());
        model.addAttribute("privacyList", getAllPrivacy());
        model.addAttribute("status", new StatusDto());

        return "status/create";
    }

    @PostMapping("/store")
    public String store(@Valid @ModelAttribute("status") StatusDto statusDto, BindingResult result, @RequestParam("images") MultipartFile[] files) throws IOException {
        if (result.hasErrors())
            return "status/create";

        Location location = locationDAO.findByLocationName(statusDto.getLocation());

        List<Attachment> attachmentList = FileUtils.saveFiles(files, Properties.STATUS_FOLDER);
        attachmentDAO.saveBulk(attachmentList);

        Status status = new Status();
        BeanUtils.copyProperties(statusDto, status);
        status.setLocation(location);
        status.setStatusAttachmentList(attachmentList);
        status.setUser(userService.getCurrentUser());
        status.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        statusDAO.save(status);

        return "redirect:/status/show/" + status.getId();
    }

    @GetMapping(value = "/show/{id}")
    public String show(Model model, @PathVariable(value = "id") String id) {

        Status status = statusDAO.findById(Long.parseLong(id));
        model.addAttribute("status", status);

        return "status/show";
    }

    @GetMapping("/all")
    public String all(Model model) {

        List<Status> statusList = statusDAO.findAll();
        model.addAttribute("statusList", statusList);

        return "status/status_feed";
    }
}

package com.fuad.controller;

import com.fuad.config.Properties;
import com.fuad.service.UserService;
import com.fuad.util.FileUtils;
import com.fuad.dao.AttachmentDAO;
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
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Controller
@RequestMapping("/status")
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

    @ResponseBody
    @PostMapping("/store")
    public String store(@Valid @ModelAttribute("status") StatusDto statusDto, BindingResult result, @RequestParam("images") MultipartFile[] files) throws IOException {
        if (result.hasErrors())
            return result.getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining("<br>"));


        List<Attachment> attachmentList = FileUtils.saveFiles(files, Properties.STATUS_FOLDER);
        attachmentDAO.saveBulk(attachmentList);

        Status status = new Status();
        BeanUtils.copyProperties(statusDto, status);
        status.setStatusAttachmentList(attachmentList);

        if(statusDto.getLocationId() != null) {
            Location location = locationDAO.findById(statusDto.getLocationId());
            status.setLocation(location);
        }

        status.setUser(userService.getCurrentUser());
        status.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        statusDAO.save(status);

        return null;
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

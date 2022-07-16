package com.fuad.controller;

import com.fuad.dao.AttachmentDAO;
import com.fuad.entity.Attachment;
import com.fuad.entity.Status;
import com.fuad.entity.User;
import com.fuad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.List;

@RestController
public class AttachmentController extends BaseController {

    @Autowired
    private AttachmentDAO attachmentDAO;

    @Autowired
    private UserService userService;

    @GetMapping("/attachment/{id}")
    public void getFile(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        Attachment attachment = attachmentDAO.findById(id);
        File file = new File(attachment.getAttachmentPath());

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        mimeType = (mimeType == null) ? "application/octet-stream" : mimeType;

        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
        response.setContentLength((int) file.length());

        FileCopyUtils.copy( new BufferedInputStream(new FileInputStream(file)), response.getOutputStream());
    }

    @GetMapping("/attachment/all")
    public ModelAndView all(Model model) {
        User currentUser = userService.getCurrentUser();

        List<Status> statusList = statusDAO.findAllByUserId(currentUser.getId());
        model.addAttribute("statusList", statusList);

        return new ModelAndView("components/gallery", "model", model);
    }

    /*
    * get attachment in bytes

    @GetMapping("/attachment/{id}")
    public byte[] attachment(@PathVariable(value="id") Long id) {

        Attachment attachment = attachmentDAO.getById(id);
        if(attachment != null) {
            byte[] bytes = FileUtils.getFile(attachment.getAttachmentPath());
            return bytes;
        }

        return null;
    }
    */
}

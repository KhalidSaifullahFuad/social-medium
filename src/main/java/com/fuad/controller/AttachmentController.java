package com.fuad.controller;

import com.fuad.config.FileUtils;
import com.fuad.dao.AttachmentDAO;
import com.fuad.entity.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class AttachmentController {

    @Autowired
    private AttachmentDAO attachmentDAO;

    @GetMapping("/attachment/{id}")
    public byte[] attachment(@PathVariable(value="id") Long id) {

        Attachment attachment = attachmentDAO.getById(id);
        if(attachment != null) {
            byte[] bytes = FileUtils.getFile(attachment.getAttachmentPath());
            String base64 = Base64.getEncoder().encodeToString(bytes);
//            return "data:image/png;base64," + base64;
            return bytes;
        }

        return null;
    }
}

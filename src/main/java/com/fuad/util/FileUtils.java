package com.fuad.util;

import com.fuad.config.Properties;
import com.fuad.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static Attachment saveFile(MultipartFile file, String folderName) throws IOException {

        if (file.isEmpty()) return null;

        String fileName = file.getOriginalFilename();
        File folder = new File(Properties.WRITE_PATH + folderName);

        if (!folder.exists()) folder.mkdirs();

        Path destinationFilePath = Paths.get(Properties.WRITE_PATH, folderName, fileName); // for fixing all the slash direction
        Files.copy(file.getInputStream(), destinationFilePath, StandardCopyOption.REPLACE_EXISTING);

        Attachment attachment = new Attachment();
        attachment.setAttachmentName(fileName);
        attachment.setAttachmentPath(destinationFilePath.toString());
        attachment.setAttachmentType(file.getContentType());

        return attachment;
    }

    public static List<Attachment> saveFiles(MultipartFile[] files, String folderName) throws IOException {
        List<Attachment> attachmentList = new ArrayList<>();

        for (MultipartFile file : files) {
            Attachment attachment = saveFile(file, folderName);
            if (attachment != null) {
                attachmentList.add(attachment);
            }
        }

        return attachmentList;
    }

}

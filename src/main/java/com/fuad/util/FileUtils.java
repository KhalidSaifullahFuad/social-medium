package com.fuad.util;

import com.fuad.config.Properties;
import com.fuad.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class FileUtils {

    public static Attachment saveFile(MultipartFile file, String folderName) throws IOException {

        if (file == null) return null;

        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        String filePath = Paths.get(Properties.WRITE_PATH, folderName, fileName).toString(); // for fixing all the slash direction

        File folder = new File(Properties.WRITE_PATH + folderName);

        if (!folder.exists()) folder.mkdirs();

        Path destinationFile = Paths.get(filePath);
        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

        Attachment attachment = new Attachment();
        attachment.setAttachmentName(fileName);
        attachment.setAttachmentPath(filePath);
        attachment.setAttachmentType(extension);

        return attachment;
    }

}

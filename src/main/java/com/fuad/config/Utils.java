package com.fuad.config;

import com.fuad.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Utils {
    public static Attachment saveFile(MultipartFile file, Long userId) {
        Attachment attachment = new Attachment();
        Path rootLocation = Paths.get(System.getProperty("user.home") + "/social-community");

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String fileExtension = fileName.substring(fileName.lastIndexOf("."));
            Path destinationFile = rootLocation.resolve(Paths.get(userId + fileExtension)).normalize().toAbsolutePath();

//            if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
//                System.err.println("Cannot store file outside current directory.");
//            }

            if (!destinationFile.toFile().exists()) {
                destinationFile.toFile().mkdirs();
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);

                attachment.setAttachmentName(fileName);
                attachment.setAttachmentPath(destinationFile.toAbsolutePath().toString());
                attachment.setAttachmentType(file.getContentType());
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        return attachment;
    }

}

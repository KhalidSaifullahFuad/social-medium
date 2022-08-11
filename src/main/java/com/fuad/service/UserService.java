package com.fuad.service;

import com.fuad.config.Properties;
import com.fuad.dao.LocationDAO;
import com.fuad.dao.UserDAO;
import com.fuad.dto.UserDto;
import com.fuad.entity.Attachment;
import com.fuad.entity.Location;
import com.fuad.entity.User;
import com.fuad.enums.Role;
import com.fuad.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    protected LocationDAO locationDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return username.contains("@") ? userDAO.findByEmail(username) : userDAO.findByUsername(username);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    public User getUserByHandle(String handle) {
        return userDAO.findByUsername(handle);
    }


    public Long insert(UserDto userDto, MultipartFile file) throws IOException {

        Location location = locationDAO.findByLocationName(userDto.getLocation());

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(Role.ROLE_USER);
        user.setLocation(location);


        // set attachment
        if(!file.isEmpty()) {
            Attachment attachment = FileUtils.saveFile(file, Properties.USER_FOLDER);
            user.setAttachment(attachment);
        }

        // set Handle
        String handle = userDto.getName().replaceAll("\\s+", "").toLowerCase();
        List<User> userList = userDAO.findByHandleLike(handle);
        if(userList.size() > 0) {
            String existingHandle = userList.get(0).getUsername();
            String numberOnly= existingHandle.replaceAll("\\D", "");
            if(numberOnly.length() > 0) {
                int number = Integer.parseInt(numberOnly) + 1;
                handle = handle + number;
            } else {
                handle = handle + "1";
            }
        }

        user.setUsername(handle);

        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        userDAO.save(user);

        return user.getId();
    }



}

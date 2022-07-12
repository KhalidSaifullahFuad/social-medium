package com.fuad.service;

import com.fuad.config.FileUtils;
import com.fuad.config.Properties;
import com.fuad.dao.LocationDAO;
import com.fuad.dao.UserDAO;
import com.fuad.dto.UserDto;
import com.fuad.entity.Attachment;
import com.fuad.entity.Location;
import com.fuad.entity.User;
import com.fuad.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    public User getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }


    public Long insert(UserDto userDto, MultipartFile file) throws IOException {
        Location location = locationDAO.findByLocationName(userDto.getLocation());

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(Role.ROLE_USER);
        user.setLocation(location);

        if(!file.isEmpty()) {
            Attachment attachment = FileUtils.saveFile(file, Properties.USER_FOLDER);
            user.setAttachment(attachment);
        }

        userDAO.save(user);

        return user.getId();
    }



}

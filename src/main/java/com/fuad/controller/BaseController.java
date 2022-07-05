package com.fuad.controller;

import com.fuad.dao.LocationDAO;
import com.fuad.dao.UserDAO;
import com.fuad.entity.Location;
import com.fuad.entity.User;
import com.fuad.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class BaseController {
    @Autowired
    protected LocationDAO locationDAO;

    @Autowired
    protected UserDAO userDAO;

    protected List<String> getAllLocation(){
        List<Location> locations = locationDAO.getAll();
        List<String> locationList = new ArrayList<>();

        for (Location location : locations) {
            locationList.add(location.getLocationName());
        }
        return locationList;
    }

    protected void createDemoUser(){

        User user = new User();
        user.setName("admin");
        user.setPassword("admin");
        user.setEmail("admin@gmail.com");
        user.setRole(Role.ROLE_ADMIN);

        userDAO.insert(user);
    }
}

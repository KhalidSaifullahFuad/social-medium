package com.fuad.controller;

import com.fuad.dao.LocationDAO;
import com.fuad.dao.StatusDAO;
import com.fuad.dao.UserDAO;
import com.fuad.entity.Location;
import com.fuad.entity.User;
import com.fuad.enums.Privacy;
import com.fuad.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class BaseController {
    @Autowired
    protected LocationDAO locationDAO;

    @Autowired
    protected UserDAO userDAO;

    @Autowired
    protected StatusDAO statusDAO;

    protected List<String> getAllLocation(){
        List<Location> locations = locationDAO.findAll();
        List<String> locationList = new ArrayList<>();

        for (Location location : locations) {
            locationList.add(location.getLocationName());
        }
        return locationList;
    }

    protected List<String> getAllPrivacy(){
        List<String> privacyList = new ArrayList<>();

        for(Privacy privacy : Privacy.values()){
            privacyList.add(privacy.label);
        }

        return privacyList;
    }

    protected List<String> getAllRole(){
        List<String> roleList = new ArrayList<>();

        for(Role role : Role.values()){
            roleList.add(role.name());
        }

        return roleList;
    }

    protected void createDemoUser(){

        List<String> locations =  getAllLocation();
        Location location;
        if(locations.size() == 0){
            location = new Location();
            location.setLocationName("Dhaka");
            locationDAO.save(location);
        }else{
            location = locationDAO.findByLocationName(locations.get(0));
        }

        User user = new User();
        user.setName("admin");
        user.setPassword("admin");
        user.setEmail("admin@gmail.com");
        user.setRole(Role.ROLE_ADMIN);
        user.setLocation(location);

        userDAO.save(user);
    }
}

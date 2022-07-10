package com.fuad.controller;

import com.fuad.dao.LocationDAO;
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

    protected List<String> getAllLocation(){
        List<Location> locations = locationDAO.getAll();
        List<String> locationList = new ArrayList<>();

        for (Location location : locations) {
            locationList.add(location.getLocationName());
        }
        return locationList;
    }

    protected List<String> getAllPrivacy(){
        List<String> privacyList = new ArrayList<>();

        for(Privacy privacy : Privacy.values()){
            privacyList.add(privacy.name());
        }

        return privacyList;
    }

    protected void createDemoUser(){

        List<String> locations =  getAllLocation();
        Location location;
        if(locations.size() == 0){
            location = new Location();
            location.setLocationName("Dhaka");
            locationDAO.save(location);
        }else{
            location = locationDAO.getByLocationName(locations.get(0));
        }

        User user = new User();
        user.setName("admin");
        user.setPassword("admin");
        user.setEmail("admin@gmail.com");
        user.setRole(Role.ROLE_ADMIN);
        user.setLocation(location);

        userDAO.insert(user);
    }
}

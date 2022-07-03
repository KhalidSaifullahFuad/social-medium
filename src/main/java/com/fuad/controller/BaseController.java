package com.fuad.controller;

import com.fuad.dao.LocationDAO;
import com.fuad.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class BaseController {
    @Autowired
    protected LocationDAO locationDAO;

    protected List<String> getAllLocation(){
        List<Location> locations = locationDAO.getAll();
        List<String> locationList = new ArrayList<>();

        for (Location location : locations) {
            locationList.add(location.getLocationName());
        }
        return locationList;
    }
}

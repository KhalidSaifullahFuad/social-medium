package com.fuad.dao;

import com.fuad.model.User;

import java.util.List;

public interface UserDAO {
    Long insert(User user);

    User getById(Long id);

    Long update(User user);

    Long delete(Long id);

    List<User> getAll();

    List<User> getAllByLocationId(Long locationId);
}

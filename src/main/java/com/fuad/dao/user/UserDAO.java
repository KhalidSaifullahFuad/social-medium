package com.fuad.dao.user;

import com.fuad.entity.User;

import java.util.List;

public interface UserDAO {
    Long insert(User user);

    Long update(User user);

    User getById(Long id);

    Long delete(Long id);

    List<User> getAll();

    List<User> getAllByLocationId(Long locationId);
}

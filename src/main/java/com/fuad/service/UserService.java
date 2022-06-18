package com.fuad.service;

import com.fuad.dao.UserDAO;
import com.fuad.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFromDb = username.contains("@") ? userDAO.getByEmail(username) : userDAO.getByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add((GrantedAuthority) () -> userFromDb.getRole().name());

        return new org.springframework.security.core.userdetails.User(userFromDb.getName(), userFromDb.getPassword(), authorities);
    }

}

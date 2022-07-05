package com.fuad.dao;

import com.fuad.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Long insert(User user) {
        Long id = -1L;
        Session session = sessionFactory.getCurrentSession();

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            id = (Long) session.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.flush();

        return id;
    }


    public Long update(User user) {
        Long id = -1L;
        Session session = sessionFactory.getCurrentSession();

        try {
            session.saveOrUpdate(user);
            id = user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.flush();

        return id;
    }

    public User getById(Long id) {
        User user = sessionFactory.getCurrentSession().load(User.class, id);
        return user;
    }

    public User getByUsername(String username) {
        List<User> users = sessionFactory.getCurrentSession()
                .createQuery("FROM User WHERE name = :username", User.class)
                .setParameter("username", username)
                .getResultList();
        return users.size() > 0 ? users.get(0) : null;
    }

    public User getByEmail(String email) {
        List<User> users = sessionFactory.getCurrentSession()
                .createQuery("FROM User WHERE email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
        return users.size() > 0 ? users.get(0) : null;
    }

    public List<User> getAll() {
        Query<User> query = sessionFactory.getCurrentSession().createQuery("FROM User", User.class);

        return query.getResultList();
    }

    public List<User> getAllByLocationId(Long locationId) {
        List<User> userList = sessionFactory.getCurrentSession()
                .createQuery("FROM User WHERE locationId = :locationId", User.class)
                .setParameter("locationId", locationId)
                .getResultList();
        return userList;
    }

}
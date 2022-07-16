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


    public Long save(User user) {
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

    public User findById(Long id) {
        return sessionFactory.getCurrentSession().load(User.class, id);
    }

    public User findByHand(String handle) {
        List<User> users = sessionFactory.getCurrentSession()
                .createQuery("FROM User WHERE handle = :handle", User.class)
                .setParameter("handle", handle.toLowerCase())
                .getResultList();
        return users.size() > 0 ? users.get(0) : null;
    }

    public User findByEmail(String email) {
        List<User> users = sessionFactory.getCurrentSession()
                .createQuery("FROM User WHERE email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
        return users.size() > 0 ? users.get(0) : null;
    }

    public List<User> findByHandleLike(String handle) {
        return sessionFactory.getCurrentSession().createQuery("FROM User u WHERE u.handle LIKE :handle ORDER BY u.id DESC", User.class)
                .setParameter("handle", handle + "%")
                .getResultList();
    }

    public List<User> findAll() {
        Query<User> query = sessionFactory.getCurrentSession().createQuery("FROM User", User.class);

        return query.getResultList();
    }

    public List<User> findAllByLocationId(Long locationId) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM User WHERE locationId = :locationId", User.class)
                .setParameter("locationId", locationId)
                .getResultList();
    }

    public int count() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(*) FROM User", Long.class)
                .getSingleResult().intValue();
    }

}
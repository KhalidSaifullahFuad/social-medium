package com.fuad.dao;

import com.fuad.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long insert(User user) {
        Long id = -1L;
        Session session = sessionFactory.getCurrentSession();

        try {
            id = (Long) session.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.flush();

        return id;
    }

    @Override
    public User getById(Long id) {
        User user = sessionFactory.getCurrentSession().get(User.class, id.toString());
        return user;
    }

    @Override
    public Long update(User user) {
        Long id = -1L;
        Session session = sessionFactory.getCurrentSession();

        try {
            session.saveOrUpdate(user);
            id = user.getId();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.flush();

        return id;
    }

    @Override
    public Long delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.load(User.class, id.toString());

        session.delete(user);
        session.flush();

        return id;
    }

    @Override
    public List<User> getAll(){
        Query query = sessionFactory.getCurrentSession().createQuery("FROM User", User.class);
        List<User> userList = query.list();

        return userList;
    }

    @Override
    public List<User> getAllByLocationId(Long locationId) {
        List<User> userList = sessionFactory.getCurrentSession().createQuery("FROM User WHERE locationId = :locationId", User.class).setParameter("locationId", locationId).list();
        return userList;
    }
}

package com.fuad.dao;

import com.fuad.entity.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LocationDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public Long insert(Location location) {
        Long id = -1L;
        Session session = sessionFactory.getCurrentSession();

        try {
            id = (Long) session.save(location);
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.flush();

        return id;
    }

    public Long update(Location location) {
        Long id = -1L;
        Session session = sessionFactory.getCurrentSession();

        try {
            session.saveOrUpdate(location);
            id = location.getId();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.flush();

        return id;
    }
    public Location getById(Long id) {
        Location location = null;
        Session session = sessionFactory.getCurrentSession();

        try {
            location = session.get(Location.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.flush();

        return location;
    }

    public Location getByName(String name) {
        Location location = null;
        Session session = sessionFactory.getCurrentSession();

        try {
            Query query = session.createQuery("FROM Location WHERE locationName = :name").setParameter("name", name);
            location = (Location) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.flush();

        return location;
    }


    public List<Location> getAll(){
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Location", Location.class);
        List<Location> locationList = query.list();

        return locationList;
    }

}

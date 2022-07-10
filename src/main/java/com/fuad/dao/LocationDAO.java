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

    public Long save(Location location) {
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
    public Location findById(Long id) {
        return sessionFactory.getCurrentSession().get(Location.class, id);
    }

    public Location findByLocationName(String locationName) {
        List<Location> locationList = sessionFactory.getCurrentSession()
                .createQuery("FROM Location WHERE locationName = :locationName", Location.class)
                .setParameter("locationName", locationName)
                .getResultList();

        return locationList.size() > 0 ? locationList.get(0) : null;
    }


    public List<Location> findAll(){
        return sessionFactory.getCurrentSession().createQuery("FROM Location", Location.class).getResultList();
    }

}

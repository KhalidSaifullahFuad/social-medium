package com.fuad.dao;

import com.fuad.entity.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StatusDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public Long save(Status status) {
        Long id = -1L;
        Session session = sessionFactory.getCurrentSession();

        try {
            id = (Long) session.save(status);
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.flush();

        return id;
    }

    public Status findById(Long id) {
        return sessionFactory.getCurrentSession().get(Status.class, id);
    }

    public Long update(Status status) {
        Long id = -1L;
        Session session = sessionFactory.getCurrentSession();

        try {
            session.saveOrUpdate(status);
            id = status.getId();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.flush();

        return id;
    }

    public List<Status> findAll(){
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Status", Status.class);
        List<Status> statusList = query.list();

        return statusList;
    }

//    public List<Status> getAllByLocationId(Long locationId) {
//        List<Status> statusList = sessionFactory.getCurrentSession().createQuery("FROM Status WHERE locationId = :locationId", Status.class).setParameter("locationId", locationId).list();
//        return statusList;
//    }
}

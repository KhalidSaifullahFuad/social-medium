package com.fuad.dao;

import com.fuad.model.Status;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
public class StatusDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public Long insert(Status status) {
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

    public Status getById(Long id) {
        Status status = sessionFactory.getCurrentSession().get(Status.class, id.toString());
        return status;
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

    public Long delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Status status = session.load(Status.class, id.toString());

        session.delete(status);
        session.flush();

        return id;
    }

    public List<Status> getAll(){
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Status", Status.class);
        List<Status> statusList = query.list();

        return statusList;
    }

    public List<Status> getAllByLocationId(Long locationId) {
        List<Status> statusList = sessionFactory.getCurrentSession().createQuery("FROM Status WHERE locationId = :locationId", Status.class).setParameter("locationId", locationId).list();
        return statusList;
    }
}

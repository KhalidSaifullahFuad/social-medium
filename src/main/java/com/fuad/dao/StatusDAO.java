package com.fuad.dao;

import com.fuad.entity.Status;
import com.fuad.entity.User;
import com.fuad.enums.Privacy;
import com.fuad.service.UserService;
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

    @Autowired
    private UserService userService;

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
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.flush();

        return id;
    }

    public List<Status> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Status s WHERE s.privacy = :privacy ORDER BY s.createdAt DESC", Status.class)
                .setParameter("privacy", Privacy.PUBLIC.label)
                .getResultList();

    }

    public List<Status> findAllByUserId(Long userId) {
        User currentUser = userService.getCurrentUser();
        String privacyHQL = (currentUser.getId().equals(userId)) ? "" : " AND s.privacy = "+Privacy.PUBLIC.label;

        return sessionFactory.getCurrentSession()
                .createQuery("FROM Status s WHERE s.user.id = :userId "+ privacyHQL + " ORDER BY s.createdAt DESC", Status.class)
                .setParameter("userId", userId)
//                .setParameter("privacy", new String[]{Privacy.PUBLIC.label, Privacy.FRIENDS.label, Privacy.PRIVATE.label})
                .getResultList();
    }

//    public List<Status> getAllByLocationId(Long locationId) {
//        List<Status> statusList = sessionFactory.getCurrentSession().createQuery("FROM Status WHERE locationId = :locationId", Status.class).setParameter("locationId", locationId).list();
//        return statusList;
//    }
}

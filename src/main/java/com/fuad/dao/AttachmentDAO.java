package com.fuad.dao;

import com.fuad.entity.Attachment;
import com.fuad.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AttachmentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Long insert(Attachment attachment) {
        Long id = -1L;
        Session session = sessionFactory.getCurrentSession();

        try {
            id = (Long) session.save(attachment);
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.flush();

        return id;
    }

    public void insertBulk(List<Attachment> attachments) {
        Session session = sessionFactory.getCurrentSession();
        try {
            attachments.forEach(session::save);
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.flush();
    }

    public Attachment findById(Long id) {
        return sessionFactory.getCurrentSession().get(Attachment.class, id);
    }
}

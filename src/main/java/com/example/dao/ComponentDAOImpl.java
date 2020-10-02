package com.example.dao;


import com.example.model.Component;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ComponentDAOImpl implements ComponentDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Component> findByType(String type) {
        Query query = entityManager.createQuery("SELECT c FROM Component c WHERE c.type = :typeName");
        query.setParameter("typeName", type);
        return query.getResultList();
    }

    @Override
    public List<Component> findAll() {
        Query query = entityManager.createQuery("FROM Component ");
        return query.getResultList();
    }

    @Override
    public List<Component> findByTypeAndModel(String type, String model) {
        String stringQuery = String.format("SELECT c FROM Component c WHERE c.type = :typeName AND c.model LIKE '%s'", model);
        Query query = entityManager.createQuery(stringQuery);
        query.setParameter("typeName", type);
        query.setParameter("model", model);
        return query.getResultList();
    }

    @Override
    public Component get(int id) {
        Session session = entityManager.unwrap(Session.class);
        Component component = session.get(Component.class, id);
        return component;
    }

    @Override
    public void save(Component component) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(component);
    }

    @Override
    public void delete(int id) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(id);
    }
}

package com.example.service;

import com.example.dao.ComponentDAO;
import com.example.model.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComponentServiceImpl implements ComponentService {

    @Autowired
    private ComponentDAO componentDAO;

    @Transactional
    @Override
    public List<Component> findByType(String type) {
        return componentDAO.findByType(type);
    }

    @Transactional
    @Override
    public List<Component> findAll() {
        return componentDAO.findAll();
    }

    @Transactional
    @Override
    public List<Component> findByTypeAndModel(String type, String model) {
        return componentDAO.findByTypeAndModel(type, model);
    }

    @Transactional
    @Override
    public Component get(int id) {
        return componentDAO.get(id);
    }

    @Transactional
    @Override
    public void save(Component component) {
        componentDAO.save(component);
    }

    @Transactional
    @Override
    public void delete(int id) {
        componentDAO.delete(id);
    }
}

package com.example.service;

import com.example.model.Component;

import java.util.List;

public interface ComponentService {
    List<Component> findByType(String type);
    List<Component> findAll();
    Component get(int id);
    void save(Component component);
    void delete(int id);
}

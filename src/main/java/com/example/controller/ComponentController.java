package com.example.controller;

import com.example.model.Component;
import com.example.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class ComponentController {
    @Autowired
    private ComponentService componentService;

    @CrossOrigin(origins = "https://sepi0.github.io/")
    @GetMapping(path = "/components/all")
    public @ResponseBody Iterable<Component> getAll() {
        return componentService.findAll();
    }

    @CrossOrigin(origins = "https://sepi0.github.io/")
    @GetMapping(path = "/components/{id}")
    public @ResponseBody Component getById(@PathVariable Integer id) {
        return componentService.get(id);
    }

    @CrossOrigin(origins = "https://sepi0.github.io/")
    @GetMapping(path = "/components/{type}/{model}")
    public @ResponseBody Iterable<Component> getByTypeAndModel(@PathVariable String type, @PathVariable String model) {
        return componentService.findByTypeAndModel(type, model);
    }

    @CrossOrigin(origins = "https://sepi0.github.io/")
    @GetMapping(path = "/components/{type}/all")
    public @ResponseBody Iterable<Component> getAllByType(@PathVariable String type) {
        return componentService.findByType(type);
    }
}

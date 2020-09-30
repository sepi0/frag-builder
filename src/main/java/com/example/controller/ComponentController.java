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

    @GetMapping(path = "/components/all")
    public @ResponseBody Iterable<Component> getAllComponents() {
        return componentService.findAll();
    }

    @GetMapping(path = "/components/{id}")
    public @ResponseBody Component getCpu(@PathVariable Integer id) {
        return componentService.get(id);
    }

    @GetMapping(path = "/components/cpu/all")
    public @ResponseBody Iterable<Component> getAllCpus() {
        return componentService.findByType("cpu");
    }
}

package com.reservasApi.packages.controller;

import com.reservasApi.packages.controller.models.Manager;
import com.reservasApi.packages.security.SecurityConstants;
import com.reservasApi.packages.service.ManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @GetMapping("/all")
    public ArrayList<Manager> getAllManagers(){
        return managerService.getManagers();
    }

    @PostMapping("/save")
    public void saveManager(@Valid @RequestBody Manager manager){
        managerService.saveManager(manager);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteManagerById(@PathVariable("id") long id){
        managerService.deleteManager(id);
    }

}

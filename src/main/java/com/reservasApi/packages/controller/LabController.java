package com.reservasApi.packages.controller;

import com.reservasApi.packages.controller.models.Access;
import com.reservasApi.packages.controller.models.Lab;
import com.reservasApi.packages.security.SecurityConstants;
import com.reservasApi.packages.service.LabService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/lab")
public class LabController {
    @Autowired
    private LabService labService;

    @GetMapping("/all")
    public ArrayList<Lab> getAllLabs(){
        return labService.getLabs();
    }

    @PostMapping("/save")
    public void saveLab(@Valid @RequestBody Lab lab){
        labService.saveLab(lab);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLabById(@PathVariable("id") long id){
         labService.deleteLab(id);
    }

    @GetMapping("/manager/{id}")
    public ArrayList<Lab> getLabsByManagerId(@PathVariable("id") long id){
        return labService.getLabsByManagerId(id);
    }

    @GetMapping("/check/{id}/{username}")
    public Access getLabAccessByIdUsername(@PathVariable("id") long id,@PathVariable("username") String username){return labService.getLabAccessByIdUsername(id,username);}
}

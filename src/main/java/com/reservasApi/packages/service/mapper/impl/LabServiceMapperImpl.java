package com.reservasApi.packages.service.mapper.impl;

import com.reservasApi.packages.controller.models.Lab;
import com.reservasApi.packages.controller.models.common.StructIdName;
import com.reservasApi.packages.repository.models.LabE;
import com.reservasApi.packages.service.mapper.LabServiceMapper;

import java.util.ArrayList;

public class LabServiceMapperImpl implements LabServiceMapper {
    public LabServiceMapperImpl(){}
    @Override
    public Lab toController(LabE labE){
        Lab lab = new Lab();
        StructIdName manager = new StructIdName();
        manager.setId(labE.getManager().getId());
        manager.setName(labE.getManager().getName());
        lab.setId(labE.getId());
        lab.setName(labE.getName());
        lab.setManager(manager);
        return lab;
    }
    @Override
    public ArrayList<Lab> toController(ArrayList<LabE> labEs){
        ArrayList<Lab> labs = new ArrayList<>();
        for(LabE labE: labEs){
            labs.add(toController(labE));
        }
        return labs;
    }
    @Override
    public LabE toRepository(Lab lab){
        LabE labE = new LabE();
        labE.setName(lab.getName());
        return labE;
    }
}

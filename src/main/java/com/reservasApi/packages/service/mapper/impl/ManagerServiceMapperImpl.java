package com.reservasApi.packages.service.mapper.impl;

import com.reservasApi.packages.controller.models.Manager;
import com.reservasApi.packages.repository.models.ManagerE;
import com.reservasApi.packages.service.mapper.ManagerServiceMapper;

import java.util.ArrayList;

public class ManagerServiceMapperImpl implements ManagerServiceMapper {

    public ManagerServiceMapperImpl(){}
    @Override
    public Manager toController(ManagerE managerE){
        Manager manager = new Manager();
        manager.setId(managerE.getId());
        manager.setName(managerE.getName());
        manager.setDuration(managerE.getDuration());
        manager.setNumberTimeSlotsDay(managerE.getNumberTimeSlotsDay());
        manager.setNumberTimeSlotsWeek(managerE.getNumberTimeSlotsWeek());
        manager.setNumberTimeSlotsTotal(managerE.getNumberTimeSlotsTotal());
        return manager;
    }
    @Override
    public ArrayList<Manager> toController(ArrayList<ManagerE> managerEs){
        ArrayList<Manager> managers = new ArrayList<>();
        for(ManagerE managerE: managerEs){
            managers.add(toController(managerE));
        }
        return managers;
    }
    @Override
    public ManagerE toRepository(Manager manager){
        ManagerE managerE = new ManagerE();
        managerE.setName(manager.getName());
        managerE.setDuration(manager.getDuration());
        managerE.setNumberTimeSlotsDay(manager.getNumberTimeSlotsDay());
        managerE.setNumberTimeSlotsWeek(manager.getNumberTimeSlotsWeek());
        managerE.setNumberTimeSlotsTotal(manager.getNumberTimeSlotsTotal());
        return managerE;
    }
}

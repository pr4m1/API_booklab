package com.reservasApi.packages.service.impl;

import com.reservasApi.packages.controller.models.Manager;
import com.reservasApi.packages.exception.ApiException;
import com.reservasApi.packages.repository.models.ManagerE;
import com.reservasApi.packages.repository.ManagerRepository;
import com.reservasApi.packages.service.ManagerService;
import com.reservasApi.packages.service.mapper.ManagerServiceMapper;
import com.reservasApi.packages.service.mapper.impl.ManagerServiceMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerRepository managerRepository;
    final private ManagerServiceMapper managerServiceMapper;

    public ManagerServiceImpl(){
        managerServiceMapper=new ManagerServiceMapperImpl();
    }

    @Override
    public ArrayList<Manager> getManagers() {
        return managerServiceMapper.toController((ArrayList<ManagerE>) managerRepository.findAll());
    }


    @Override
    public void saveManager(Manager manager) {
        checkProperties(manager);
        ManagerE managerE = managerServiceMapper.toRepository(manager);
        managerRepository.save(managerE);
    }

    @Override
    public Manager getManagerById(long id) {
        ManagerE managerE = managerRepository.findById(id).orElseThrow(()->new ApiException("Not manager with id '" + id + "' exists."));
        return managerServiceMapper.toController(managerE);
    }


    @Override
    public void deleteManager(long id) {
        Manager manager = getManagerById(id);
        managerRepository.deleteById(manager.getId());

    }

    private void checkProperties(Manager manager){
        if(manager.getNumberTimeSlotsDay()<1) {
            throw new ApiException("The number of slots per day must be greater than 0");
        }else if(manager.getNumberTimeSlotsDay()>manager.getNumberTimeSlotsWeek()){
            throw new ApiException("The number of slots per week must be equal to or greater than the number of slots per day");
        }else if(manager.getNumberTimeSlotsWeek()>manager.getNumberTimeSlotsTotal()){
            throw new ApiException("The number of total slots must be equal to or greater than the number of slots per week");
        }else if(manager.getDuration()<1){
            throw new ApiException("The duration must be greater than 0");
        }else if(manager.getName().length()==0){
            throw new ApiException("The name field can not be empty");
        }
    }

}

package com.reservasApi.packages.service.impl;

import com.reservasApi.packages.controller.models.Access;
import com.reservasApi.packages.controller.models.Lab;
import com.reservasApi.packages.exception.ApiException;
import com.reservasApi.packages.repository.models.ManagerE;
import com.reservasApi.packages.repository.models.LabE;
import com.reservasApi.packages.repository.ManagerRepository;
import com.reservasApi.packages.repository.LabRepository;
import com.reservasApi.packages.service.LabService;
import com.reservasApi.packages.service.mapper.LabServiceMapper;
import com.reservasApi.packages.service.mapper.impl.LabServiceMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class LabServiceImpl implements LabService {
    @Autowired
    private LabRepository labRepository;
    @Autowired
    private ManagerRepository managerRepository;
    final private LabServiceMapper labServiceMapper;

    public LabServiceImpl(){
        labServiceMapper=new LabServiceMapperImpl();
    }

    @Override
    public ArrayList<Lab> getLabs() {
        return labServiceMapper.toController((ArrayList<LabE>) labRepository.findAll());
    }

    @Override
    public void saveLab(Lab lab) {
        ManagerE managerE = managerRepository.findById(lab.getManager().getId()).orElseThrow(()->new ApiException("Manager does not exist"));
        checkProperties(lab);
        LabE labE = labServiceMapper.toRepository(lab);
        labE.setManager(managerE);
        labRepository.save(labE);
    }

    @Override
    public Lab getLabById(long id) {
        LabE labE = labRepository.findById(id).orElseThrow(()->new ApiException("Not lab with id '" + id + "' exists."));
        return labServiceMapper.toController(labE);
    }


    @Override
    public void deleteLab(long id) {
        Lab lab = getLabById(id);
        labRepository.deleteById(lab.getId());
    }

    @Override
    public ArrayList<Lab> getLabsByManagerId(long id){
        return labServiceMapper.toController(labRepository.getLabsByManagerId(id));
    }

    @Override
    public Access getLabAccessByIdUsername(long id, String username) {
        return getLabAccessByIdUsernameTime(id,username,LocalDateTime.now(ZoneOffset.UTC));
    }

    private void checkProperties(Lab lab){
        if(lab.getName().length()==0){
            throw new ApiException("The name field can not be empty");
        }
    }

    private Access getLabAccessByIdUsernameTime(long id, String username, LocalDateTime timestamp) {
        return new Access(labRepository.isUserLabTime(timestamp,id,username));
    }
}

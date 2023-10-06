package com.reservasApi.packages.service;

import com.reservasApi.packages.controller.models.Access;
import com.reservasApi.packages.controller.models.Lab;


import java.util.ArrayList;

public interface LabService {
    ArrayList<Lab> getLabs();
    void saveLab(Lab lab);
    Lab getLabById(long id);
    void deleteLab(long id);
    ArrayList<Lab> getLabsByManagerId(long id);
    Access getLabAccessByIdUsername(long id,String username);
}

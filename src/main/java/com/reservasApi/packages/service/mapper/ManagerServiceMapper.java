package com.reservasApi.packages.service.mapper;

import com.reservasApi.packages.controller.models.Manager;
import com.reservasApi.packages.repository.models.ManagerE;

import java.util.ArrayList;

public interface ManagerServiceMapper {
    Manager toController(ManagerE managerE);
    ArrayList<Manager> toController(ArrayList<ManagerE> managerEs);
    ManagerE toRepository(Manager manager);
}

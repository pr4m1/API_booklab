package com.reservasApi.packages.service;

import com.reservasApi.packages.controller.models.Manager;

import java.util.ArrayList;

public interface ManagerService {
    ArrayList<Manager> getManagers();
    void saveManager(Manager manager);
    Manager getManagerById(long id);
    void deleteManager(long id);
}

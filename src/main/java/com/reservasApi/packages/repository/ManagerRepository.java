package com.reservasApi.packages.repository;

import com.reservasApi.packages.repository.models.ManagerE;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends CrudRepository<ManagerE,Long> {

    }

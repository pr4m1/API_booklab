package com.reservasApi.packages.service.mapper;

import com.reservasApi.packages.controller.models.Lab;
import com.reservasApi.packages.controller.models.common.StructIdName;
import com.reservasApi.packages.repository.models.LabE;

import java.util.ArrayList;

public interface LabServiceMapper {
    Lab toController(LabE labE);
    ArrayList<Lab> toController(ArrayList<LabE> labEs);
    LabE toRepository(Lab lab);
}

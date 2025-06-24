package com.niit.adminservice.service;

import com.niit.adminservice.dao.ClinicRepository;
import com.niit.adminservice.entity.Clinic;
import org.springframework.stereotype.Service;

@Service
public class ClinicService {

    private final ClinicRepository clinicRepository;

    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public void addClinic(Clinic clinic) {
        clinicRepository.saveAndFlush(clinic);
    }
}

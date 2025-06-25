package com.niit.adminservice.service;

import com.niit.adminservice.dao.ClinicRepository;
import com.niit.adminservice.entity.Clinic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicService {

    private final ClinicRepository clinicRepository;

    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    public void saveClinic(Clinic clinic) {
        clinicRepository.saveAndFlush(clinic);
    }
}

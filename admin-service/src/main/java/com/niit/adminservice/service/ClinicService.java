package com.niit.adminservice.service;

import com.niit.adminservice.dao.ClinicRepository;
import com.niit.adminservice.dao.DepartmentRepository;
import com.niit.adminservice.entity.Clinic;
import com.niit.adminservice.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicService {

    private final ClinicRepository clinicRepository;
    private final DepartmentRepository departmentRepository;

    public ClinicService(ClinicRepository clinicRepository, DepartmentRepository departmentRepository) {
        this.clinicRepository = clinicRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    public Clinic getClinicById(int id) {
        return clinicRepository.findById(id).isPresent() ? clinicRepository.findById(id).get() : null;
    }

    public void saveClinic(Clinic clinic) {
        clinicRepository.saveAndFlush(clinic);
    }

    public Department getDepartmentById(int id) {
        return departmentRepository.findById(id).isPresent() ? departmentRepository.findById(id).get() : null;
    }
}

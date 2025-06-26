package com.niit.patientservice.service;

import com.niit.patientservice.dao.ClinicRepository;
import com.niit.patientservice.dao.DepartmentRepository;
import com.niit.patientservice.entity.Clinic;
import com.niit.patientservice.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClinicService {

    private final ClinicRepository clinicRepository;
    private final DepartmentRepository departmentRepository;

    public ClinicService(ClinicRepository clinicRepository, DepartmentRepository departmentRepository) {
        this.clinicRepository = clinicRepository;
        this.departmentRepository = departmentRepository;
    }

    public Map<Department, List<Clinic>> getAllClinicsSorted() {
        List<Clinic> clinics = clinicRepository.findAll();
        return clinics.stream().collect(Collectors.groupingBy(Clinic::getDepartment));
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}

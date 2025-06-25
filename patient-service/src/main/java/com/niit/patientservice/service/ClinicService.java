package com.niit.patientservice.service;

import com.niit.patientservice.dao.ClinicRepository;
import com.niit.patientservice.entity.Clinic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClinicService {

    private final ClinicRepository clinicRepository;

    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public Map<Integer, List<Clinic>> getAllClinicsSorted() {
        List<Clinic> clinics = clinicRepository.findAll();
        return clinics.stream().collect(Collectors.groupingBy(Clinic::getDepartmentId));
    }
}

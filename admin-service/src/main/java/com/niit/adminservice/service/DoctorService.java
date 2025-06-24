package com.niit.adminservice.service;

import com.niit.adminservice.dao.DoctorRepository;
import com.niit.adminservice.entity.Doctor;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }
}

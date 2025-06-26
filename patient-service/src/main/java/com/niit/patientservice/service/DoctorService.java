package com.niit.patientservice.service;

import com.niit.patientservice.dao.DoctorRepository;
import com.niit.patientservice.entity.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor getDoctorById(int id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public List<Doctor> getDoctorsByClinicId(int clinicId) {
        return doctorRepository.findDoctorsByClinicId(clinicId);
    }
}

package com.niit.adminservice.service;

import com.niit.adminservice.dao.ClinicRepository;
import com.niit.adminservice.dao.DoctorRepository;
import com.niit.adminservice.entity.Doctor;
import com.niit.adminservice.entity.DoctorWithClinicName;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ClinicRepository clinicRepository;

    public DoctorService(DoctorRepository doctorRepository, ClinicService clinicService, ClinicRepository clinicRepository) {
        this.doctorRepository = doctorRepository;
        this.clinicRepository = clinicRepository;
    }

    public List<DoctorWithClinicName> getAllDoctorsWithClinicName() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorWithClinicName> doctorsWithClinicName = new ArrayList<>();
        for (Doctor doctor : doctors) {
            String clinicName = clinicRepository.findById(doctor.getClinicId()).getName();
            DoctorWithClinicName doctorWithClinicName = new DoctorWithClinicName(doctor, clinicName);
            doctorsWithClinicName.add(doctorWithClinicName);
        }
        return doctorsWithClinicName;
    }

    public void saveDoctor(Doctor doctor) {
        doctorRepository.saveAndFlush(doctor);
    }
}

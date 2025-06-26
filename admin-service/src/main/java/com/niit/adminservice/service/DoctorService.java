package com.niit.adminservice.service;

import com.niit.adminservice.dao.ClinicRepository;
import com.niit.adminservice.dao.DoctorRepository;
import com.niit.adminservice.entity.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ClinicRepository clinicRepository;

    public DoctorService(DoctorRepository doctorRepository, ClinicService clinicService, ClinicRepository clinicRepository) {
        this.doctorRepository = doctorRepository;
        this.clinicRepository = clinicRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public void saveDoctor(Doctor doctor) {
        doctorRepository.saveAndFlush(doctor);
    }

    public boolean editDoctor(Doctor doctor) {
        if (doctorRepository.findById(doctor.getId()).isEmpty()) {
            return false;
        }
        Doctor editedDoctor = doctorRepository.findById(doctor.getId()).get();

        editedDoctor.setName(doctor.getName());
        editedDoctor.setRole(doctor.getRole());
        editedDoctor.setClinic(doctor.getClinic());
        editedDoctor.setDesc(doctor.getDesc());
        if (doctor.getPhotoBlob() != null && doctor.getPhotoBase64() != null) {
            editedDoctor.setPhotoBlob(doctor.getPhotoBlob());
            editedDoctor.setPhotoBase64(doctor.getPhotoBase64());
        }

        saveDoctor(editedDoctor);
        return true;
    }
}

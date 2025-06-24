package com.niit.patientservice.service;

import com.niit.patientservice.dao.PatientRepository;
import com.niit.patientservice.entity.Patient;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final PatientRepository patientRepository;

    public LoginService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient login(String username, String password) {
        return patientRepository.findByUsernameAndPassword(username, password);
    }

    public Patient register(Patient patient) {
        return patientRepository.saveAndFlush(patient);
    }
}

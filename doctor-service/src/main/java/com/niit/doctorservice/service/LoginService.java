package com.niit.doctorservice.service;

import com.niit.doctorservice.dao.DoctorAccountRepository;
import com.niit.doctorservice.dao.DoctorRepository;
import com.niit.doctorservice.entity.Doctor;
import com.niit.doctorservice.entity.DoctorAccount;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final DoctorAccountRepository doctorAccountRepository;
    private final DoctorRepository doctorRepository;

    public LoginService(DoctorAccountRepository doctorAccountRepository, DoctorRepository doctorRepository) {
        this.doctorAccountRepository = doctorAccountRepository;
        this.doctorRepository = doctorRepository;
    }

    public Doctor login(String username, String password) {
        return doctorAccountRepository.findByUsernameAndPassword(username, password).getDoctor();
    }

    public DoctorAccount register(String username, String password, int doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        if (doctor == null) {
            return null;
        }
        DoctorAccount doctorAccount = new DoctorAccount(username, password, doctor);
        return doctorAccountRepository.saveAndFlush(doctorAccount);
    }
}

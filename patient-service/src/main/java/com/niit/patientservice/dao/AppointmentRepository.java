package com.niit.patientservice.dao;

import com.niit.patientservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    Appointment findByPatientIdAndDoctorIdAndClinicIdAndDateAndTime(int patientId, int doctorId, int clinicId, LocalDate date, char time);
}

package com.niit.doctorservice.dao;

import com.niit.doctorservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findByDoctorId(int doctorId);

    List<Appointment> findByDoctorIdAndAndDateAndTime(int doctorId, LocalDate date, char time);
}

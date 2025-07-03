package com.niit.adminservice.dao;

import com.niit.adminservice.entity.Doctor;
import com.niit.adminservice.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
    Optional<Schedule> findByDoctorAndDateAndTime(Doctor doctor, LocalDate date, char time);
}

package com.niit.doctorservice.dao;

import com.niit.doctorservice.entity.Doctor;
import com.niit.doctorservice.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    Optional<Schedule> findByDoctorAndDateAndTime(Doctor doctor, LocalDate date, char time);
}

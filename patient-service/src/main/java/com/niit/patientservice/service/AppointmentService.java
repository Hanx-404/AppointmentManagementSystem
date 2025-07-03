package com.niit.patientservice.service;

import com.niit.patientservice.dao.AppointmentRepository;
import com.niit.patientservice.dao.ScheduleRepository;
import com.niit.patientservice.entity.Appointment;
import com.niit.patientservice.entity.Schedule;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ScheduleRepository scheduleRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, ScheduleRepository scheduleRepository) {
        this.appointmentRepository = appointmentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public boolean saveAppointment(Appointment appointment) {
        if (appointmentRepository.findByPatientIdAndDoctorIdAndClinicIdAndDateAndTime(
                appointment.getPatient().getId(),
                appointment.getDoctor().getId(),
                appointment.getClinic().getId(),
                appointment.getDate(),
                appointment.getTime()
        ) != null) return false;
        appointmentRepository.saveAndFlush(appointment);
        Schedule schedule = scheduleRepository.findByDoctorAndDateAndTime(appointment.getDoctor(), appointment.getDate(), appointment.getTime())
                .orElse(new Schedule(appointment, 0));
        schedule.setCount(schedule.getCount() + 1);
        scheduleRepository.saveAndFlush(schedule);
        return true;
    }
}

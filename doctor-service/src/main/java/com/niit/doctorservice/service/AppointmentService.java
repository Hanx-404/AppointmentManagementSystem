package com.niit.doctorservice.service;

import com.niit.doctorservice.dao.AppointmentRepository;
import com.niit.doctorservice.entity.Appointment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByDoctorIdAndDateAndTime(int doctorId, LocalDate date, char time) {
        return appointmentRepository.findByDoctorIdAndAndDateAndTime(doctorId, date, time);
    }

    public boolean call(int id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment == null || appointment.getState() != Appointment.AppointmentState.WAITING) {
            return false;
        }
        appointment.setState(Appointment.AppointmentState.ACTIVE);
        appointmentRepository.saveAndFlush(appointment);
        return true;
    }

    public boolean complete(int id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment == null || appointment.getState() != Appointment.AppointmentState.ACTIVE) {
            return false;
        }
        appointment.setState(Appointment.AppointmentState.COMPLETED);
        appointmentRepository.saveAndFlush(appointment);
        return true;
    }
}

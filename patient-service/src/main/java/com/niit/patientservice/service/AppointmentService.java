package com.niit.patientservice.service;

import com.niit.patientservice.dao.AppointmentRepository;
import com.niit.patientservice.entity.Appointment;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
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
        return true;
    }
}

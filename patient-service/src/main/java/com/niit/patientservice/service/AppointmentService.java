package com.niit.patientservice.service;

import com.niit.patientservice.dao.AppointmentRepository;
import com.niit.patientservice.entity.Appointment;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<Map<String, String>> generateTimeSlots() {
        List<Map<String, String>> timeSlots = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (int i = 0; i < 3; i++) {
            LocalDate d = today.plusDays(i);
            DayOfWeek dow = d.getDayOfWeek();
            String week = switch (dow) {
                case MONDAY -> "周一";
                case TUESDAY -> "周二";
                case WEDNESDAY -> "周三";
                case THURSDAY -> "周四";
                case FRIDAY -> "周五";
                case SATURDAY -> "周六";
                case SUNDAY -> "周日";
            };
            timeSlots.add(Map.of(
                    "datetime", d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "-AM",
                    "label", d.format(DateTimeFormatter.ofPattern("MM-dd")) + " " + week + " 上午"
            ));
            timeSlots.add(Map.of(
                    "datetime", d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "-PM",
                    "label", d.format(DateTimeFormatter.ofPattern("MM-dd")) + " " + week + " 下午"
            ));
        }
        return timeSlots;
    }
}

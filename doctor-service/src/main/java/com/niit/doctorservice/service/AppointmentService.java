package com.niit.doctorservice.service;

import com.niit.doctorservice.dao.AppointmentRepository;
import com.niit.doctorservice.dao.ScheduleRepository;
import com.niit.doctorservice.entity.Appointment;
import com.niit.doctorservice.entity.Schedule;
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
    private final ScheduleRepository scheduleRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, ScheduleRepository scheduleRepository) {
        this.appointmentRepository = appointmentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
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

        Schedule schedule = scheduleRepository.findByDoctorAndDateAndTime(appointment.getDoctor(), appointment.getDate(), appointment.getTime())
                .orElse(null);
        if (schedule != null) {
            schedule.setCount(schedule.getCount() - 1);
            scheduleRepository.saveAndFlush(schedule);
        }
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
                    "date", d.toString(),
                    "time", "A",
                    "label", d.format(DateTimeFormatter.ofPattern("MM-dd")) + " " + week + " 上午"
            ));
            timeSlots.add(Map.of(
                    "date", d.toString(),
                    "time", "P",
                    "label", d.format(DateTimeFormatter.ofPattern("MM-dd")) + " " + week + " 下午"
            ));
        }
        return timeSlots;
    }
}

package com.niit.patientservice.service;

import com.niit.patientservice.dao.DoctorRepository;
import com.niit.patientservice.dao.ScheduleRepository;
import com.niit.patientservice.entity.Doctor;
import com.niit.patientservice.entity.Schedule;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final DoctorRepository doctorRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, DoctorRepository doctorRepository) {
        this.scheduleRepository = scheduleRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<Schedule> getSchedulesByClinicIdAndDateAndTime(int clinicId, LocalDate date, char time) {
        List<Schedule> schedules = new ArrayList<>();
        for (Doctor doctor : doctorRepository.findDoctorsByClinicId(clinicId)) {
            Schedule schedule = scheduleRepository.findByDoctorAndDateAndTime(doctor, date, time)
                    .orElse(new Schedule(doctor, date, time, 0));
            schedules.add(schedule);
        }
        return schedules;
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

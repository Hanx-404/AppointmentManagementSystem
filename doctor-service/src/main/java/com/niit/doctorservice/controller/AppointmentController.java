package com.niit.doctorservice.controller;

import com.niit.doctorservice.entity.Doctor;
import com.niit.doctorservice.service.AppointmentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/doctor")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointments/{date}/{time}")
    public String appointments(@PathVariable LocalDate date, @PathVariable char time, HttpSession session, Model model) {
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        int doctorId = doctor.getId();
        model.addAttribute("appointments", appointmentService.getAppointmentsByDoctorIdAndDateAndTime(doctorId, date, time));
        model.addAttribute("timeSlots", appointmentService.generateTimeSlots());

        return "appointments";
    }

    @PostMapping("/call/{id}")
    @ResponseBody
    public ResponseEntity<String> call(@PathVariable("id") int id) {
        if (appointmentService.call(id)) {
            return ResponseEntity.ok("叫号成功！");
        } else {
            return ResponseEntity.status(400).body("叫号失败！");
        }
    }

    @PostMapping("/complete/{id}")
    @ResponseBody
    public ResponseEntity<String> complete(@PathVariable("id") int id) {
        if (appointmentService.complete(id)) {
            return ResponseEntity.ok("已标记为完成！");
        } else {
            return ResponseEntity.status(400).body("完成失败！");
        }
    }
}

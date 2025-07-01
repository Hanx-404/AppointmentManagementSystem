package com.niit.doctorservice.controller;

import com.niit.doctorservice.entity.Doctor;
import com.niit.doctorservice.service.AppointmentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/doctor")
public class IndexController {

    private final AppointmentService appointmentService;

    public IndexController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/index")
    public String index(HttpSession session, Model model) {
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        model.addAttribute("appointments", appointmentService.getAppointmentsByDoctorId(doctor.getId()));
        model.addAttribute("timeSlots", appointmentService.generateTimeSlots());

        return "appointments";
    }
}

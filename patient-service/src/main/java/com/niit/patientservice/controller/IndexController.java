package com.niit.patientservice.controller;

import com.niit.patientservice.service.AppointmentService;
import com.niit.patientservice.service.ClinicService;
import com.niit.patientservice.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    private final ClinicService clinicService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    public IndexController(ClinicService clinicService, DoctorService doctorService, AppointmentService appointmentService) {
        this.clinicService = clinicService;
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        // model.addAttribute("departments", clinicService.getAllDepartments());
        model.addAttribute("clinicsMap", clinicService.getAllClinicsSorted());
        return "index";
    }

    @GetMapping("/clinic/{clinicId}")
    public String clinic(@PathVariable int clinicId, Model model) {
        model.addAttribute("timeSlots", appointmentService.generateTimeSlots());
        model.addAttribute("doctors", doctorService.getDoctorsByClinicId(clinicId));
        return "registration";
    }

    // @GetMapping("/clinic/{clinicId}/date/{date}/time/{time}")
    // public String clinic(@PathVariable int clinicId, @PathVariable String date, @PathVariable char time, Model model) {
    //     // TODO
    //     model.addAttribute("doctors", doctorService.getDoctorsByClinicId(clinicId));
    //     return "registration";
    // }
}

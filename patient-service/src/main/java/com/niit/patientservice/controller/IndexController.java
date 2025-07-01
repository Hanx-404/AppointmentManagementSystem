package com.niit.patientservice.controller;

import com.niit.patientservice.service.ClinicService;
import com.niit.patientservice.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@Controller
public class IndexController {

    private final ClinicService clinicService;
    private final ScheduleService scheduleService;

    public IndexController(ClinicService clinicService, ScheduleService scheduleService) {
        this.clinicService = clinicService;
        this.scheduleService = scheduleService;
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

    @GetMapping("/registration/{clinicId}")
    public String registrationDefault(@PathVariable int clinicId, Model model) {
        LocalDate date = LocalDate.now();
        char time = 'A';
        return "redirect:/registration/" + clinicId + "/" + date + "/" + time;
    }

    @GetMapping("/registration/{clinicId}/{date}/{time}")
    public String registration(@PathVariable int clinicId,
                               @PathVariable LocalDate date,
                               @PathVariable char time,
                               Model model) {
        model.addAttribute("date", date);
        model.addAttribute("time", time);
        model.addAttribute("clinicId", clinicId);
        model.addAttribute("timeSlots", scheduleService.generateTimeSlots());
        model.addAttribute("schedule", scheduleService.getSchedulesByClinicIdAndDateAndTime(clinicId, date, time));
        return "registration";
    }
}

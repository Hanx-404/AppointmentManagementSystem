package com.niit.patientservice.controller;

import com.niit.patientservice.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final ClinicService clinicService;

    public IndexController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("clinics", clinicService.getAllClinicsSorted());
        return "home";
    }
}

package com.niit.adminservice.controller;

import com.niit.adminservice.entity.Clinic;
import com.niit.adminservice.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClinicController {

    private final ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @PostMapping("/addClinic")
    public String addClinic(@RequestParam String name,
                            @RequestParam String department) {
        Clinic clinic = new Clinic(name, department);
        clinicService.addClinic(clinic);
        return "redirect:/admin";   // TODO
    }
}

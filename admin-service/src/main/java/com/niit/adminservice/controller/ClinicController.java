package com.niit.adminservice.controller;

import com.niit.adminservice.entity.Clinic;
import com.niit.adminservice.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClinicController {

    private final ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    // 新增门诊
    @PostMapping("/addClinic")
    public String addClinic(@RequestParam String name,
                            @RequestParam String department) {
        Clinic clinic = new Clinic(name, department);
        clinicService.saveClinic(clinic);
        return "redirect:/admin";   // TODO
    }

    // 修改门诊
    @PostMapping("/editClinic")
    public String editClinic(@ModelAttribute Clinic clinic) {
        clinicService.saveClinic(clinic);
        return "redirect:/admin";   // TODO
    }
}

package com.niit.adminservice.controller;

import com.niit.adminservice.entity.Clinic;
import com.niit.adminservice.entity.Department;
import com.niit.adminservice.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String addClinic(Model model,
                            @RequestParam String name,
                            @RequestParam int departmentId) {
        Department department = clinicService.getDepartmentById(departmentId);
        if (department == null) {
            model.addAttribute("errorMsg", "找不到科室！");
            return "redirect:/error";
        }

        Clinic clinic = new Clinic(name, department);
        clinicService.saveClinic(clinic);
        return "redirect:/management#clinics";
    }

    // 修改门诊
    @PostMapping("/editClinic")
    public String editClinic(@ModelAttribute Clinic clinic) {
        clinicService.saveClinic(clinic);
        return "redirect:/management#clinics";
    }
}

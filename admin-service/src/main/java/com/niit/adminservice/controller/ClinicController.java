package com.niit.adminservice.controller;

import com.niit.adminservice.entity.Clinic;
import com.niit.adminservice.entity.Result;
import com.niit.adminservice.entity.Department;
import com.niit.adminservice.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/clinic")
public class ClinicController {

    private final ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    // 新增门诊
    @PostMapping("/add")
    public String addClinic(Model model,
                            @RequestParam String clinicName,
                            @RequestParam String departmentName) {

        clinicService.insertClinicAndDepartment(clinicName, departmentName);

        return "redirect:/index#clinics";

    }

    // 修改门诊
    @PostMapping("/edit")
    public String editClinic(Model model,
                                      @RequestParam Integer id,
                             @RequestParam String clinicName,
                             @RequestParam String departmentName) {
        clinicService.updateClinicAndDepartment(id,clinicName,departmentName);
        return "redirect:/index#clinics";
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public Result<Boolean> deleteClinic(@PathVariable Integer id){
        return clinicService.deleteClinicById(id);
    }
}

package com.niit.adminservice.controller;

import com.niit.adminservice.entity.Patient;
import com.niit.adminservice.entity.Result;
import com.niit.adminservice.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: HeiLong
 * @CreateTime: 2025-07-01)
 */

@Controller
@RequestMapping("/admin/patient")
public class PatientController {

    private final PatientService patientService;
    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping()
    public String getAllPatient(Model model,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "15") int size){
        Page<Patient> patientPage = patientService.getAllPatient(page, size);
        model.addAttribute("patients", patientPage.getContent());
        model.addAttribute("currentPage", patientPage.getNumber());
        model.addAttribute("totalPages", patientPage.getTotalPages());
        model.addAttribute("totalItems", patientPage.getTotalElements());
        return "patient-management";
    }

    @PostMapping("add")
    public String insertPatient(Patient patient){
        patientService.insertPatient(patient);
        return "redirect:/admin/patient";
    }

    @PostMapping("edit")
    public String updatePatient(Patient patient){
        patientService.updatePatient(patient);
        return "redirect:/admin/patient";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deletePatient(@PathVariable Integer id){
        patientService.deletePatient(id);
        return "redirect:/admin/patient";
    }


}

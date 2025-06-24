package com.niit.adminservice.controller;

import com.niit.adminservice.dao.ClinicRepository;
import com.niit.adminservice.entity.Clinic;
import com.niit.adminservice.service.ClinicService;
import com.niit.adminservice.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final ClinicService clinicService;
    private final DoctorService doctorService;
    private final ClinicRepository clinicRepository;

    public IndexController(ClinicService clinicService, DoctorService doctorService, ClinicRepository clinicRepository) {
        this.clinicService = clinicService;
        this.doctorService = doctorService;
        this.clinicRepository = clinicRepository;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("clinics", clinicService.getAllClinics());
        model.addAttribute("doctors", doctorService.getAllDoctorsWithClinicName());
        model.addAttribute("departments", clinicRepository.findAllDepartments());
        model.addAttribute("clinic", new Clinic());
        return "management";
    }
}

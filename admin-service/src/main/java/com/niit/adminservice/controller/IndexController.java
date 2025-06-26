package com.niit.adminservice.controller;

import com.niit.adminservice.dao.ClinicRepository;
import com.niit.adminservice.entity.Clinic;
import com.niit.adminservice.service.ClinicService;
import com.niit.adminservice.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    // 访问管理页面
    @GetMapping("/index")
    public String index(Model model,
//                        @RequestParam(name = "clinicPage",defaultValue = "0") int clinicPage,
//                        @RequestParam(name = "clinicSize",defaultValue = "15") int clinicSize,
                        @RequestParam(name = "doctorPage",defaultValue = "0") int doctorPage,
                        @RequestParam(name = "doctorSize",defaultValue = "15") int doctorSize
                        ) {
        // TODO
        //model.addAttribute("clinics", clinicService.getAllClinics(clinicPage, clinicSize));
        model.addAttribute("clinics", clinicService.getAllClinics());
        model.addAttribute("doctors", doctorService.getAllDoctors(doctorPage, doctorSize));
        model.addAttribute("departments", clinicRepository.findAll());
        model.addAttribute("clinic", new Clinic());
        return "management";
    }
}

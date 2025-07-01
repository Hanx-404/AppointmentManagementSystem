package com.niit.adminservice.controller;

import com.niit.adminservice.dao.ClinicRepository;
import com.niit.adminservice.dao.PatientRepository;
import com.niit.adminservice.entity.Clinic;
import com.niit.adminservice.service.ClinicService;
import com.niit.adminservice.service.DoctorService;
import com.niit.adminservice.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@CrossOrigin(origins = "*")
@Controller
public class IndexController {

    private final ClinicService clinicService;
    private final DoctorService doctorService;
    private final ClinicRepository clinicRepository;
    private final PatientService patientService;

    public IndexController(ClinicService clinicService, DoctorService doctorService, ClinicRepository clinicRepository, PatientService patientService) {
        this.clinicService = clinicService;
        this.doctorService = doctorService;
        this.clinicRepository = clinicRepository;
        this.patientService = patientService;
    }

    // 访问管理页面
    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "doctorPage",defaultValue = "0") int doctorPage,
                        @RequestParam(name = "doctorSize",defaultValue = "15") int doctorSize,
                        @RequestParam(name = "clinicPage", defaultValue = "0") int clinicPage,
                        @RequestParam(name = "clinicSize", defaultValue = "15") int clinicSize
                        ) {
        model.addAttribute("clinics", clinicService.getAllClinics());
        model.addAttribute("doctors", doctorService.getAllDoctors(doctorPage, doctorSize));
        model.addAttribute("departments", clinicRepository.findAll());
        model.addAttribute("clinic", new Clinic());
//        model.addAttribute("patients",patientService.getAllPatient());
        // 添加分页参数
        model.addAttribute("doctorPage", doctorPage);
        model.addAttribute("doctorSize", doctorSize);
        model.addAttribute("clinicPage", clinicPage);
        model.addAttribute("clinicSize", clinicSize);
        return "management";
    }
}

package com.niit.adminservice.controller;

import com.niit.adminservice.entity.Doctor;
import com.niit.adminservice.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // 新增医生
    @PostMapping("/addDoctor")
    public String addDoctor(@RequestParam String name,
                            @RequestParam String role,
                            @RequestParam int clinicId,
                            @RequestParam MultipartFile photoFile,
                            @RequestParam String desc) throws IOException {
        if (photoFile.isEmpty()) {
            return "redirect:/error";
        }
        byte[] photoBlob = photoFile.getBytes();
        Doctor doctor = new Doctor(name, role, clinicId, photoBlob, desc);
        doctorService.saveDoctor(doctor);
        return "redirect:/admin/doctors";
    }

    // 修改医生
    @PostMapping("/editDoctor")
    public String editDoctor(@ModelAttribute Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return "redirect:/admin";   // TODO
    }
}

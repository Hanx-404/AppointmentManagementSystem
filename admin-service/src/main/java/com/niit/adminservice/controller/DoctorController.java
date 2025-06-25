package com.niit.adminservice.controller;

import com.niit.adminservice.entity.Doctor;
import com.niit.adminservice.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Controller
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

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
        String photoBase64 = Base64.getEncoder().encodeToString(photoBlob);
        Doctor doctor = new Doctor(name, role, clinicId, photoBlob, photoBase64,desc);
        doctorService.saveDoctor(doctor);
        return "redirect:/admin/doctors";
    }
}

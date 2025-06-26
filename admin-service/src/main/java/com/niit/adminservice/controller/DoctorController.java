package com.niit.adminservice.controller;

import com.niit.adminservice.entity.Clinic;
import com.niit.adminservice.entity.Doctor;
import com.niit.adminservice.entity.Result;
import com.niit.adminservice.service.ClinicService;
import com.niit.adminservice.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping("/admin/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final ClinicService clinicService;

    public DoctorController(DoctorService doctorService, ClinicService clinicService) {
        this.doctorService = doctorService;
        this.clinicService = clinicService;
    }

    @PostMapping("/add")
    public String addDoctor(Model model,
                            @RequestParam String name,
                            @RequestParam String role,
                            @RequestParam int clinicId,
                            @RequestParam MultipartFile photoFile,
                            @RequestParam String desc) throws IOException {
        if (photoFile.isEmpty()) {
            model.addAttribute("errorMsg", "请选择图片！");
            return "redirect:/error";
        }

        Clinic clinic = clinicService.getClinicById(clinicId);
        if (clinic == null) {
            model.addAttribute("errorMsg", "找不到诊所！");
            return "redirect:/error";
        }

        byte[] photoBlob = photoFile.getBytes();
        String photoBase64 = Base64.getEncoder().encodeToString(photoBlob);
        Doctor doctor = new Doctor(name, role, clinic, photoBlob, photoBase64, desc);
        doctorService.saveDoctor(doctor);
        return "redirect:/index#doctors";
    }

    @PostMapping("/edit")
    public String editDoctor(Model model,
                             @RequestParam int id,
                             @RequestParam String name,
                             @RequestParam String role,
                             @RequestParam int clinicId,
                             @RequestParam MultipartFile photoFile,
                             @RequestParam String desc) throws IOException {
        byte[] photoBlob = null;
        String photoBase64 = null;

        if (!photoFile.isEmpty()) {
            photoBlob = photoFile.getBytes();
            photoBase64 = Base64.getEncoder().encodeToString(photoBlob);
        }

        Clinic clinic = clinicService.getClinicById(clinicId);

        Doctor doctor = new Doctor(id, name, role, clinic, photoBlob, photoBase64, desc);
        if (doctorService.editDoctor(doctor)) {
            return "redirect:/index#doctors";
        } else {
            model.addAttribute("errorMsg", "编辑医生错误！");
            return "redirect:/error";
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result<Boolean> deleteDoctorById(@PathVariable("id") Integer id){
        return doctorService.deleteDoctorById(id);
    }
}

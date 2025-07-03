package com.niit.adminservice.controller;

import com.niit.adminservice.entity.Appointment;
import com.niit.adminservice.service.AppointmentService;
import com.niit.adminservice.service.ClinicService;
import com.niit.adminservice.service.DoctorService;
import com.niit.adminservice.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: HeiLong
 * @CreateTime: 2025-07-03)
 */

@Controller
@RequestMapping("/admin/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final ClinicService clinicService;

    public AppointmentController(AppointmentService appointmentService,
                                 PatientService patientService,
                                 DoctorService doctorService,
                                 ClinicService clinicService) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.clinicService = clinicService;
    }

    @GetMapping()
    public String getAllAppointments(Model model,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "15") int size) {
        Page<Appointment> appointmentPage = appointmentService.getAllAppointments(page, size);
        model.addAttribute("appointments", appointmentPage.getContent());
        model.addAttribute("currentPage", appointmentPage.getNumber());
        model.addAttribute("totalPages", appointmentPage.getTotalPages());
        model.addAttribute("totalItems", appointmentPage.getTotalElements());

        // 添加下拉选择所需的数据
        model.addAttribute("patients", patientService.getAllPatient(page,size));
        model.addAttribute("doctors", doctorService.getAllDoctors(page,size));
        model.addAttribute("clinics", clinicService.getAllClinics());
        model.addAttribute("states", Appointment.AppointmentState.values());

        return "appointment-management";
    }

    @PostMapping("/add")
    public String addAppointment(Appointment appointment) {
        appointmentService.saveAppointment(appointment);
        return "redirect:/admin/appointment";
    }

    @PostMapping("/edit")
    public String updateAppointment(Appointment appointment) {
        appointmentService.updateAppointment(appointment);
        return "redirect:/admin/appointment";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteAppointment(@PathVariable Integer id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/admin/appointment";
    }



}

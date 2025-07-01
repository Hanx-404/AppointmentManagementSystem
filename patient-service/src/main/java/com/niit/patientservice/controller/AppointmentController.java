package com.niit.patientservice.controller;

import com.niit.patientservice.entity.Appointment;
import com.niit.patientservice.entity.Doctor;
import com.niit.patientservice.entity.Patient;
import com.niit.patientservice.service.AppointmentService;
import com.niit.patientservice.service.DoctorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;

    public AppointmentController(AppointmentService appointmentService, DoctorService doctorService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
    }

    @PostMapping("/registerAppointment")
    @ResponseBody
    public ResponseEntity<String> registerAppointment(@RequestParam("doctorId") int doctorId,
                                                      @RequestParam("date") LocalDate date,
                                                      @RequestParam("time") char time,
                                                      HttpSession session) {
        Patient patient = (Patient) session.getAttribute("patient");
        Doctor doctor = doctorService.getDoctorById(doctorId);

        Appointment appointment = new Appointment(patient, doctor, doctor.getClinic(), date, time);
        if (appointmentService.saveAppointment(appointment)) {
            return ResponseEntity.ok("挂号成功");
        } else {
            return ResponseEntity.status(406).body("请勿重复挂号！");
        }
    }
}

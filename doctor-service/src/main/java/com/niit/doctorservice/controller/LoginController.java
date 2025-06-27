package com.niit.doctorservice.controller;

import com.niit.doctorservice.entity.Doctor;
import com.niit.doctorservice.entity.DoctorAccount;
import com.niit.doctorservice.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/doctor")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {
        Doctor doctor = loginService.login(username, password);
        if (doctor != null) {
            session.setAttribute("doctor", doctor);
            return "redirect:/doctor/index";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam int doctorId) {
        if (loginService.register(username, password, doctorId) != null) {
            return "redirect:/doctor/login";
        } else {
            return "redirect:/error";
        }
    }
}

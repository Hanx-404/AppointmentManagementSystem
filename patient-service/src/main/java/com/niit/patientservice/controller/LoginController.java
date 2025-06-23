package com.niit.patientservice.controller;

import com.niit.patientservice.entity.Patient;
import com.niit.patientservice.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login(HttpSession session, String username, String password) {
        Patient patient = loginService.login(username, password);
        if (patient != null) {
            session.setAttribute("patient", patient);
            return "login";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/register")
    public String register(Patient patient) {
        if (loginService.register(patient) != null) {
            return "redirect:/login";
        } else {
            return "redirect:/error";
        }
    }
}

package com.niit.patientservice.controller;

import com.niit.patientservice.entity.Patient;
import com.niit.patientservice.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {
        Patient patient = loginService.login(username, password);
        if (patient != null) {
            session.setAttribute("patient", patient);
            return "redirect:/home";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam("realName") String name,
                           @RequestParam boolean gender,
                           @RequestParam int age) {
        Patient patient = new Patient(username, password, name, gender, age);
        if (loginService.register(patient) != null) {
            return "redirect:/login";
        } else {
            return "redirect:/error";
        }
    }
}

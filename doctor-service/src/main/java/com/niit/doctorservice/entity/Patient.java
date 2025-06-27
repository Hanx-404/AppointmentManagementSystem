package com.niit.doctorservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String name;
    private boolean gender;
    private int age;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    public Patient(String username, String password, String name, boolean gender, int age) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
}

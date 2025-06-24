package com.niit.patientservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int patientId;
    private int doctorId;
    private int clinicId;
    private String date;
    private char schedule;

    public Appointment(int patientId, int doctorId, int clinicId, String date, char schedule) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.clinicId = clinicId;
        this.date = date;
        this.schedule = schedule;
    }
}

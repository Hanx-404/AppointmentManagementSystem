package com.niit.patientservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int patientId;
    private int doctorId;
    private int clinicId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private char time;
    @Enumerated(EnumType.STRING)
    private AppointmentState state;

    public enum AppointmentState {
        WAITING, ACTIVE, COMPLETED
    }

    public Appointment(int patientId, int doctorId, int clinicId, LocalDate date, char time, AppointmentState state) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.clinicId = clinicId;
        this.date = date;
        this.time = time;
        this.state = state;
    }
}

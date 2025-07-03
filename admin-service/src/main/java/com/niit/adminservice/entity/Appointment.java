package com.niit.adminservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

/**
 * @Author: HeiLong
 * @CreateTime: 2025-07-03)
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "clinic_id", referencedColumnName = "id")
    private Clinic clinic;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private char time;
    @Enumerated(EnumType.STRING)
    private AppointmentState state;

    public enum AppointmentState {
        WAITING, ACTIVE, COMPLETED
    }

    public Appointment(Patient patient, Doctor doctor, Clinic clinic, LocalDate date, char time) {
        this.patient = patient;
        this.doctor = doctor;
        this.clinic = clinic;
        this.date = date;
        this.time = time;
        this.state = AppointmentState.WAITING;
    }
}

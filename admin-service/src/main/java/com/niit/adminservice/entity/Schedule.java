package com.niit.adminservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;
    private LocalDate date;
    private char time;
    private int count;

    public Schedule(Doctor doctor, LocalDate date, char time, int count) {
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.count = count;
    }

    public Schedule(Appointment appointment, int count) {
        this.doctor = appointment.getDoctor();
        this.date = appointment.getDate();
        this.time = appointment.getTime();
        this.count = count;
    }
}
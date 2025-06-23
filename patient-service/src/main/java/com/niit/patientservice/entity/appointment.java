package com.niit.patientservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int patientId;
    private int doctorId;
    private int clinicId;
    private String date;
    private char schedule;
}

package com.niit.patientservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String role;
    private int clinicId;
    @Lob
    private byte[] photo;
    private String desc;
}

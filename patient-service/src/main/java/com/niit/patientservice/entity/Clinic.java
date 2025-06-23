package com.niit.patientservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String department;
}

package com.niit.adminservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

    public Doctor(String name, String role, int clinicId, byte[] photo, String desc) {
        this.name = name;
        this.role = role;
        this.clinicId = clinicId;
        this.photo = photo;
        this.desc = desc;
    }
}

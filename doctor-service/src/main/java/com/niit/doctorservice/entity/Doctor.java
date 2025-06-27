package com.niit.doctorservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String role;
    @ManyToOne
    @JoinColumn(name = "clinic_id", referencedColumnName = "id")
    private Clinic clinic;
    @Lob
    private byte[] photoBlob;
    @Lob
    private String photoBase64;
    private String desc;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;
    @OneToOne(mappedBy = "doctor")
    private DoctorAccount doctorAccount;

    public Doctor(String name, String role, Clinic clinic, byte[] photoBlob, String photoBase64, String desc) {
        this.name = name;
        this.role = role;
        this.clinic = clinic;
        this.photoBlob = photoBlob;
        this.photoBase64 = photoBase64;
        this.desc = desc;
    }
}

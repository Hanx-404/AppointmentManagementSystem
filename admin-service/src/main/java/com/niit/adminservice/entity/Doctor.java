package com.niit.adminservice.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private byte[] photoBlob;
    @Lob
    private String photoBase64;
    @Column(name = "`desc`")
    private String desc;

    public Doctor(String name, String role, int clinicId, byte[] photoBlob, String photoBase64,String desc) {
        this.name = name;
        this.role = role;
        this.clinicId = clinicId;
        this.photoBlob = photoBlob;
        this.photoBase64 = photoBase64;
        this.desc = desc;
    }
}

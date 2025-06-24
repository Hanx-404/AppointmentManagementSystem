package com.niit.adminservice.entity;

import lombok.Data;

@Data
public class DoctorWithClinicName {
    private int id;
    private String name;
    private String role;
    private int clinicId;
    private byte[] photo;
    private String desc;
    private String clinicName;

    public DoctorWithClinicName(Doctor doctor, String clinicName) {
        this.id = doctor.getId();
        this.name = doctor.getName();
        this.role = doctor.getRole();
        this.clinicId = doctor.getClinicId();
        this.photo = doctor.getPhoto();
        this.desc = doctor.getDesc();
        this.clinicName = clinicName;
    }
}

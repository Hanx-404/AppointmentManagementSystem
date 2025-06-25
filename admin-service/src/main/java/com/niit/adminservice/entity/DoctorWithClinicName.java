package com.niit.adminservice.entity;

import lombok.Data;

@Data
public class DoctorWithClinicName {
    private int id;
    private String name;
    private String role;
    private int clinicId;
//    private byte[] photoBlob;
    private String photoBase64;
    private String desc;
    private String clinicName;

    public DoctorWithClinicName(Doctor doctor, String clinicName) {
        this.id = doctor.getId();
        this.name = doctor.getName();
        this.role = doctor.getRole();
        this.clinicId = doctor.getClinicId();
        this.photoBase64 = doctor.getPhotoBase64();
        this.desc = doctor.getDesc();
        this.clinicName = clinicName;
    }
}

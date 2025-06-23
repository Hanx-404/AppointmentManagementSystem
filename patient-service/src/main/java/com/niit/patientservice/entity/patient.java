package com.niit.patientservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String name;
    private boolean gender;
    private int age;
}

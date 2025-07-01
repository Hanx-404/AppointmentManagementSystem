package com.niit.adminservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: HeiLong
 * @CreateTime: 2025-07-01)
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String name;
    private int gender;
    private int age;
}

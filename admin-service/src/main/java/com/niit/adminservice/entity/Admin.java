package com.niit.adminservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: HeiLong
 * @CreateTime: 2025-06-30)
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin_account")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
}

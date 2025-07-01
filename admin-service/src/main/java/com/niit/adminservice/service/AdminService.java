package com.niit.adminservice.service;

import com.niit.adminservice.dao.AdminRepository;
import com.niit.adminservice.entity.Admin;
import com.niit.adminservice.entity.Result;
import org.springframework.stereotype.Service;

/**
 * @Author: HeiLong
 * @CreateTime: 2025-06-30)
 */

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }
    public Admin findByName(String username){
        Admin admin = adminRepository.findAdminByUsername(username);
        return admin;
    }
    public boolean checkUsernameAndPassword(String username,String password){
        Admin admin = adminRepository.findByUsernameAndPassword(username, password);
        return admin != null;
    }
}

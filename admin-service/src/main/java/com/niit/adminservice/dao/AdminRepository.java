package com.niit.adminservice.dao;

import com.niit.adminservice.entity.Admin;
import com.niit.adminservice.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findAdminByUsername(String username);
    Admin findByUsernameAndPassword(String username,String password);
}

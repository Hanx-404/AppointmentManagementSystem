package com.niit.adminservice.dao;

import com.niit.adminservice.entity.Department;
import com.niit.adminservice.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Department findByName(String name);
}

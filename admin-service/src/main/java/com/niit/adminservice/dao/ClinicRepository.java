package com.niit.adminservice.dao;

import com.niit.adminservice.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {

    @Query("SELECT DISTINCT c.department FROM Clinic c WHERE c.department IS NOT NULL")
    List<String> findAllDepartments();
}

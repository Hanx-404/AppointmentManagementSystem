package com.niit.adminservice.dao;

import com.niit.adminservice.entity.Clinic;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {

    Clinic findByName(String clinicName);

//    Page<Clinic> findAll(Pageable pageable);

//    @SQLInsert("insert into clinic where clinicName = #{clinicName} and departmentId = #{departmentId}")
//    Clinic insertClinicAndDepartment(String clinicName,Integer departmentId);

//    @Query("SELECT * FROM Clinic")
//    List<String> findAllDepartments();
}

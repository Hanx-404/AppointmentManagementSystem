package com.niit.doctorservice.dao;

import com.niit.doctorservice.entity.DoctorAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorAccountRepository extends JpaRepository<DoctorAccount, Integer> {
    DoctorAccount findByUsernameAndPassword(String username, String password);
}

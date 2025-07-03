package com.niit.adminservice.service;

import com.niit.adminservice.dao.PatientRepository;
import com.niit.adminservice.entity.Patient;
import com.niit.adminservice.entity.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: HeiLong
 * @CreateTime: 2025-07-01)
 */

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }

    public Page<Patient> getAllPatient(int page, int size){
        return patientRepository.findAll(PageRequest.of(page,size));
    }

    public Result<Boolean> insertPatient(Patient patient){
        if (!(patient.getAge()>0 && patient.getAge()<120)){
            return Result.fail("年龄输入有误，请重新输入");
        }
        Patient save = patientRepository.saveAndFlush(patient);
        if(save != null){
            return Result.success(true,"添加成功");
        }else {
            return Result.fail("添加失败");
        }
    }

    public Result<Boolean> updatePatient(Patient patient){
        if(patientRepository.findById(patient.getId()).isEmpty()){
            return Result.fail("更新失败，该病人不存在");
        }
        if (!(patient.getAge()>0 && patient.getAge()<120)){
            return Result.fail("年龄输入有误，请重新输入");
        }
        Patient patientById = patientRepository.findById(patient.getId()).get();
        patientById.setUsername(patient.getUsername());
        patientById.setPassword(patient.getPassword());
        patientById.setName(patient.getName());
        patientById.setAge(patient.getAge());
        patientById.setGender(patient.getGender());
        Patient save = patientRepository.save(patientById);
        if(save != null){
            return Result.success(true,"添加成功");
        }else {
            return Result.fail("添加失败");
        }
    }


    public Result<Boolean> deletePatient(Integer id){
        if (patientRepository.existsById(id)){
            patientRepository.deleteById(id);
            return Result.success(true,"删除成功");
        }else {
            return Result.fail("删除失败,病人id不存在：" + id);
        }

    }

}

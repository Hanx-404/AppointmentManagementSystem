package com.niit.adminservice.service;

import com.niit.adminservice.dao.ClinicRepository;
import com.niit.adminservice.dao.DepartmentRepository;
import com.niit.adminservice.entity.Clinic;
import com.niit.adminservice.entity.Department;
import com.niit.adminservice.entity.Result;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicService {

    private final ClinicRepository clinicRepository;
    private final DepartmentRepository departmentRepository;

    public ClinicService(ClinicRepository clinicRepository, DepartmentRepository departmentRepository) {
        this.clinicRepository = clinicRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    public Clinic getClinicById(int id) {
        return clinicRepository.findById(id).isPresent() ? clinicRepository.findById(id).get() : null;
    }

    public void saveClinic(Clinic clinic) {
        clinicRepository.saveAndFlush(clinic);
    }

    public Department getDepartmentById(int id) {
        return departmentRepository.findById(id).isPresent() ? departmentRepository.findById(id).get() : null;
    }

    /**
     * 修改门诊跟科室
     * @param id
     * @param clinicName
     * @param departmentName
     * @return
     */

    public Result<Boolean> updateClinicAndDepartment(Integer id,String clinicName,String departmentName){
        Department department = departmentRepository.findByName(departmentName);
        System.out.println(department);
        Clinic clinic = clinicRepository.findById(id).get();

        if(department != null){
            clinic.setName(clinicName);
            clinic.setDepartment(department);
            clinicRepository.save(clinic);
            return Result.success(true,"修改门诊成功");
        }else {
            department=new Department();
            department.setName(departmentName);
            Department save = departmentRepository.save(department);

            clinic.setName(clinicName);
            clinic.setDepartment(save);
            clinicRepository.save(clinic);
            return Result.success(true,"修改门诊成功");
        }
    }

    /**
     * 新增门诊
     * @param clinicName
     * @param departmentName
     * @return
     */
    public Result<Boolean> insertClinicAndDepartment(String clinicName,String departmentName){
        Department department = departmentRepository.findByName(departmentName);
        if(department != null){
            return Result.fail("对不起，该科室已存在");
        }
        department=new Department();
        department.setName(departmentName);
        Department save = departmentRepository.save(department);


        Clinic clinic = new Clinic();
        clinic.setName(clinicName);
        clinic.setDepartment(department);
        //Clinic clinic = clinicRepository.findByName(clinicName);
        clinicRepository.save(clinic);
        return Result.success("新增科室成功");
    }

    public Result<Boolean> deleteClinicById(Integer id){
        if (clinicRepository.existsById(id)){
            clinicRepository.deleteById(id);
            return Result.success(true,"门诊删除成功");
        }else {
            return Result.fail("删除失败，门诊id不存在" + id);
        }
    }
}

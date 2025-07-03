package com.niit.adminservice.service;

import com.niit.adminservice.dao.AppointmentRepository;
import com.niit.adminservice.dao.ScheduleRepository;
import com.niit.adminservice.entity.Appointment;
import com.niit.adminservice.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @Author: HeiLong
 * @CreateTime: 2025-07-03)
 */

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final ScheduleRepository scheduleRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,ScheduleRepository scheduleRepository) {
        this.appointmentRepository = appointmentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    // 分页获取所有预约记录
    public Page<Appointment> getAllAppointments(int page, int size) {
        return appointmentRepository.findAll(PageRequest.of(page, size));
    }

    // 添加预约记录
    /*public void addAppointment(Appointment appointment) {
        // 如果状态为null，设置默认状态
        if (appointment.getState() == null) {
            appointment.setState(Appointment.AppointmentState.WAITING);
        }
        appointmentRepository.save(appointment);
    }*/

    public boolean saveAppointment(Appointment appointment) {
        if (appointmentRepository.findByPatientIdAndDoctorIdAndClinicIdAndDateAndTime(
                appointment.getPatient().getId(),
                appointment.getDoctor().getId(),
                appointment.getClinic().getId(),
                appointment.getDate(),
                appointment.getTime()
        ) != null) return false;
        appointmentRepository.saveAndFlush(appointment);
        Schedule schedule = scheduleRepository.findByDoctorAndDateAndTime(appointment.getDoctor(), appointment.getDate(), appointment.getTime())
                .orElse(new Schedule(appointment, 0));
        schedule.setCount(schedule.getCount() + 1);
        scheduleRepository.saveAndFlush(schedule);
        return true;
    }

    // 更新预约记录
    public void updateAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    // 删除预约记录
    public void deleteAppointment(Integer id) {
        appointmentRepository.deleteById(id);
    }

    // 根据ID获取预约记录
    public Appointment getAppointmentById(Integer id) {
        return appointmentRepository.findById(id).orElse(null);
    }
}

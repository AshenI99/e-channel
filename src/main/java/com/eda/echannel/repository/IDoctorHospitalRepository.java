package com.eda.echannel.repository;

import com.eda.echannel.model.DoctorHospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDoctorHospitalRepository extends JpaRepository<DoctorHospital, Long> {
    List<DoctorHospital> findAllByDoctorId(Long Id);
}

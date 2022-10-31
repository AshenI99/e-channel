package com.eda.echannel.repository;

import com.eda.echannel.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepository extends JpaRepository <Doctor, Long> {

}

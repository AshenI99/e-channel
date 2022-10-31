package com.eda.echannel.repository;

import com.eda.echannel.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepository extends JpaRepository <Appointment, Long> {
}

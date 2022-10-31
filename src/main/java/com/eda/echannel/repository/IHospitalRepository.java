package com.eda.echannel.repository;

import com.eda.echannel.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHospitalRepository extends JpaRepository <Hospital, Long> {
}

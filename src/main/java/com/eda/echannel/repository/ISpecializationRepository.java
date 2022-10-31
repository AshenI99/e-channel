package com.eda.echannel.repository;

import com.eda.echannel.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISpecializationRepository extends JpaRepository <Specialization, Long> {
}

package com.eda.echannel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctor-hospital")
public class DoctorHospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doctor_id", nullable = false, length=100)
    private Long doctorId;

    @OneToOne
    @JoinColumn(name = "hospital_id", nullable = false, referencedColumnName = "hospital_id")
    private Hospital hospital;

}

package com.eda.echannel.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "channel")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "hospital_id")
    private Long hospitalId;

    @Column(name = "specialization_id")
    private Long specializationId;

    @Column(name = "date_time")
    private String dateTime;

    @Column(name = "fee")
    private Double fee;

    @Column(name = "maximum_patients")
    private Integer maximumPatients;

    @Column(name = "active_patients")
    private Integer activePatients;
}

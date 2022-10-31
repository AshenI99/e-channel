package com.eda.echannel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Table(name = "appointment")

public class Appointment {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "appointmentId")
    private Long appointmentId;

    @Column (name = "appointmentDate",length = 15)
    private Long appointmentDate;

    @Column (name = "appointmentTime", length=10)
    private Long appointmentTime;

    @Column (name = "patientNIC",length =12)
    private String patientNIC;

    @Column (name = "patientEmail",length = 100)
    private String patientEmail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospitalId", insertable = false, updatable = false)
    private Hospital hospital;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Doctor doctor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specializationId", insertable = false, updatable = false)
    private Specialization specialization;

}

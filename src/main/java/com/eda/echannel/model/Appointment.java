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
    @Column (name = "patientID")
    private Long appointmentId;

    @Column (name = "appointmentDate",length = 15)
    private Date appointmentDate;

    @Column (name = "appointmentTime", length=10)
    private ZonedDateTime appointmentTime;

    @Column (name = "patientNIC",length =12)
    private String patientNIC;

    @Column (name = "patientEmail",length = 100)
    private String patientEmail;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospitalId", insertable = false, updatable = false)
    private List<Hospital> hospitalList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private List<Doctor> doctorList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "specializationId", insertable = false, updatable = false)
    private List<Specialization> specializationList = new ArrayList<>();

}

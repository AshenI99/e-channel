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

@Table(name = "appointment")

public class Appointment {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "appointmentId")
    private Long appointmentId;

    @Column (name = "appointmentNumber")
    private Integer appointmentNumber;

    @Column (name = "patientNIC",length =12)
    private String patientNIC;

    @Column (name = "patientName",length =12)
    private String patientName;

    @Column (name = "patientEmail",length = 100)
    private String patientEmail;

    @Column (name = "channel_id")
    private Long channelId;
}

package com.eda.echannel.dto.response;

import com.eda.echannel.model.Doctor;
import com.eda.echannel.model.Hospital;
import com.eda.echannel.model.Specialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseDto {
    private Long appointmentId;
    private Date appointmentDate;
    private ZonedDateTime appointmentTime;
    private String patientNIC;
    private String patientEmail;
    private Hospital hospital;
    private Doctor doctor;
    private Specialization specialization;
}

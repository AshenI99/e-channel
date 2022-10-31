package com.eda.echannel.dto.request;

import com.eda.echannel.dto.response.SearchResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestDto {
    private Long appointmentId;
    private String appointmentDate;
    private Long appointmentTime;
    private Integer appointmentNumber;
    private String patientNIC;
    private String patientEmail;
    private SearchResponseDto channel;
}

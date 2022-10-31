package com.eda.echannel.dto.request;

import com.eda.echannel.dto.response.SearchResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestDto {
    private Long channelId;
    private String patientName;
    private String patientNIC;
    private String patientEmail;
    private String patientMobile;
}

package com.eda.echannel.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalResponseDto {

    private Long hospitalId;
    private String HospitalName;
    private String location;
}

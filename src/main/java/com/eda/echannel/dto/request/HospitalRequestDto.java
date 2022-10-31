package com.eda.echannel.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalRequestDto {
    private Long hospitalId;
    private String HospitalName;
    private String location;
}

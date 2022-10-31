package com.eda.echannel.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecializationResponseDto {
    private Long specializationId;
    private String specializationName;
}

package com.eda.echannel.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequestDto {

    private Long doctorId;
    private Long hospitalId;
    private Long specializationId;
    private String date;
}

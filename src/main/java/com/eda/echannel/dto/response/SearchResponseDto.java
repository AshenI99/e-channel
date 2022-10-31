package com.eda.echannel.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponseDto {

    private Long id;
    private String doctorName;
    private String hospitalName;
    private String specializationName;
    private Date date;
    private Integer maximumPatients;
}

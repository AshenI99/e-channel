package com.eda.echannel.dto.request;

import com.eda.echannel.model.Hospital;
import com.eda.echannel.model.Specialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequestDto {
    private Long id;
    private String name;
    private List<Specialization> specializations;
    private List<Hospital> hospitals;

}

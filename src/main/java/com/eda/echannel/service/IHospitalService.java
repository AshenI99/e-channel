package com.eda.echannel.service;

import com.eda.echannel.dto.response.HospitalResponseDto;
import com.eda.echannel.model.Hospital;

import java.util.List;

public interface IHospitalService {

    public List<Hospital> getAll() throws Exception;

    public HospitalResponseDto findHospitalbyId(Long hospitalId) throws Exception;

    public HospitalResponseDto convertHospitalToHospitalResponseDto(Hospital hospital) throws Exception;
}

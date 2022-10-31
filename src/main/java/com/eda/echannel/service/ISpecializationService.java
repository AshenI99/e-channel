package com.eda.echannel.service;

import com.eda.echannel.dto.response.SpecializationResponseDto;
import com.eda.echannel.model.Hospital;
import com.eda.echannel.model.Specialization;

import java.util.List;

public interface ISpecializationService {
    public List<Specialization> getAll() throws Exception;

    public SpecializationResponseDto findSpecializationbyId(Long specializationId) throws Exception;

    public SpecializationResponseDto convertSpecializationToSpecializationResponseDto(Specialization specialization) throws Exception;
}

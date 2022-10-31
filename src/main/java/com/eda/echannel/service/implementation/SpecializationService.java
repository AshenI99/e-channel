package com.eda.echannel.service.implementation;

import com.eda.echannel.dto.response.HospitalResponseDto;
import com.eda.echannel.dto.response.SpecializationResponseDto;
import com.eda.echannel.model.Hospital;
import com.eda.echannel.model.Specialization;
import com.eda.echannel.repository.IHospitalRepository;
import com.eda.echannel.repository.ISpecializationRepository;
import com.eda.echannel.service.ISpecializationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecializationService implements ISpecializationService {
    private final ISpecializationRepository specializationRepository;

    @Autowired
    public SpecializationService(
            ISpecializationRepository specializationRepository
    ){
        this.specializationRepository = specializationRepository;
    }

    @Override
    public List<Specialization> getAll() throws Exception {

        try {
            return specializationRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public SpecializationResponseDto findSpecializationbyId(Long specializationId) throws Exception {
        try {
            Optional<Specialization> foundSpecializations = specializationRepository.findById(specializationId);

            if (foundSpecializations.isPresent()) {
                Specialization foundSpecialization = foundSpecializations.get();
                SpecializationResponseDto specializationResponseDto = convertSpecializationToSpecializationResponseDto(foundSpecialization);
                return specializationResponseDto;
            } else {
                throw new Exception("Specialization with the ID " +specializationId+ " not found");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public SpecializationResponseDto convertSpecializationToSpecializationResponseDto(Specialization specialization) throws Exception {
        SpecializationResponseDto specializationResponseDto = new SpecializationResponseDto();
        BeanUtils.copyProperties(specialization, specializationResponseDto);
        return specializationResponseDto;
    }
}

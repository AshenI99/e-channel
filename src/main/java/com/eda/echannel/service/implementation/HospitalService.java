package com.eda.echannel.service.implementation;

import com.eda.echannel.dto.response.HospitalResponseDto;
import com.eda.echannel.dto.request.HospitalRequestDto;
import com.eda.echannel.model.Hospital;
import com.eda.echannel.repository.IHospitalRepository;
import com.eda.echannel.service.IHospitalService;
import com.eda.echannel.util.InputValidatorUtil;
import com.eda.echannel.util.MessagesAndContent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalService implements IHospitalService {

    private final IHospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(
            IHospitalRepository hospitalRepository
    ){
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public List<Hospital> getAll() throws Exception {

        try {
            return hospitalRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public HospitalResponseDto findHospitalbyId(Long hospitalId) throws Exception {
        try {
            Optional<Hospital> foundHospitals = hospitalRepository.findById(hospitalId);

            if (foundHospitals.isPresent()) {
                Hospital foundHospital = foundHospitals.get();
                HospitalResponseDto hospitalResponseDto = convertHospitalToHospitalResponseDto(foundHospital);
                return hospitalResponseDto;
            } else {
                throw new Exception("Hospital with the ID " +hospitalId+ " not found");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public HospitalResponseDto convertHospitalToHospitalResponseDto(Hospital hospital) throws Exception {
        HospitalResponseDto hospitalResponseDto = new HospitalResponseDto();
        BeanUtils.copyProperties(hospital, hospitalResponseDto);
        return hospitalResponseDto;
    }

    @Override
    public HospitalResponseDto create(HospitalRequestDto request) throws Exception {
        try {

            Hospital hospitalRequest = new Hospital();

            if (request == null) {
                throw new Exception("Invalid input data.");
            }

            String hospitalName = InputValidatorUtil.validateStringProperty(MessagesAndContent.HOSPITAL_01, request.getHospitalName(), "Hospital Name", 150);
            hospitalRequest.setHospitalName(hospitalName);

            String location = InputValidatorUtil.validateStringProperty(MessagesAndContent.HOSPITAL_01, request.getLocation(), "Location", 100);
            hospitalRequest.setLocation(location);

            long currentTime = Instant.now().getEpochSecond();

            Hospital savedHospital = hospitalRepository.save(hospitalRequest);

            HospitalResponseDto hospitalDto = convertHospitalToHospitalResponseDto(savedHospital);

            return hospitalDto;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

}

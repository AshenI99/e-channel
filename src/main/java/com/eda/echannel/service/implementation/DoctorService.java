package com.eda.echannel.service.implementation;

import com.eda.echannel.dto.response.DoctorResponseDto;
import com.eda.echannel.model.Doctor;
import com.eda.echannel.repository.IDoctorRepository;
import com.eda.echannel.service.IDoctorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService implements IDoctorService {

    private final IDoctorRepository doctorRepository;

    @Autowired
    public DoctorService(
            IDoctorRepository doctorRepository
    ){
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> getAll() throws Exception {

        try {
            return doctorRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public DoctorResponseDto findDoctorById(Long doctorId) throws Exception {
        try {
            Optional<Doctor> foundDoctors = doctorRepository.findById(doctorId);

            if (foundDoctors.isPresent()) {
                Doctor foundDoctor = foundDoctors.get();
                DoctorResponseDto doctorResponseDto = convertDoctorToDoctorResponseDto(foundDoctor);
                return doctorResponseDto;
            } else {
                throw new Exception("Doctor with the ID " +doctorId+ " not found");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public DoctorResponseDto convertDoctorToDoctorResponseDto(Doctor doctor) throws Exception {
        DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
        BeanUtils.copyProperties(doctor, doctorResponseDto);
        return doctorResponseDto;
    }
}

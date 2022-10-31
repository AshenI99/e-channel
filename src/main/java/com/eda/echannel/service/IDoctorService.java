package com.eda.echannel.service;

import com.eda.echannel.dto.request.DoctorRequestDto;
import com.eda.echannel.dto.response.DoctorResponseDto;
import com.eda.echannel.model.Doctor;

import java.util.List;

public interface IDoctorService {

    List<Doctor> getAll() throws Exception;

    DoctorResponseDto findDoctorById(Long doctorId) throws Exception;

    DoctorResponseDto createDoctor(DoctorRequestDto doctor) throws Exception;

    DoctorResponseDto updateDoctor(DoctorRequestDto doctor) throws Exception;

    void deleteDoctor(Long id) throws Exception;

    DoctorResponseDto convertDoctorToDoctorResponseDto(Doctor doctor) throws Exception;

}

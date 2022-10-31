package com.eda.echannel.service;

import com.eda.echannel.dto.request.DoctorRequestDto;
import com.eda.echannel.dto.response.DoctorResponseDto;
import com.eda.echannel.model.Doctor;

import java.util.List;

public interface IDoctorService {

    public List<Doctor> getAll() throws Exception;

    public DoctorResponseDto findDoctorById(Long doctorId) throws Exception;

    public DoctorResponseDto createDoctor(DoctorRequestDto doctor) throws Exception;

    public DoctorResponseDto updateDoctor(DoctorRequestDto doctor) throws Exception;

    public void deleteDoctor(Long id) throws Exception;

    public DoctorResponseDto convertDoctorToDoctorResponseDto(Doctor doctor) throws Exception;

}

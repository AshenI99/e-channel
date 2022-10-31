package com.eda.echannel.service.implementation;

import com.eda.echannel.dto.request.DoctorRequestDto;
import com.eda.echannel.dto.response.DoctorResponseDto;
import com.eda.echannel.model.Doctor;
import com.eda.echannel.model.DoctorHospital;
import com.eda.echannel.model.Hospital;
import com.eda.echannel.repository.IDoctorHospitalRepository;
import com.eda.echannel.repository.IDoctorRepository;
import com.eda.echannel.repository.IHospitalRepository;
import com.eda.echannel.service.IDoctorService;
import com.eda.echannel.util.InputValidatorUtil;
import com.eda.echannel.util.MessagesAndContent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService implements IDoctorService {

    private final IDoctorRepository doctorRepository;
    private final IDoctorHospitalRepository doctorHospitalRepository;
    private final IHospitalRepository hospitalRepository;

    @Autowired
    public DoctorService(
            IDoctorRepository doctorRepository,
            IDoctorHospitalRepository doctorHospitalRepository,
            IHospitalRepository hospitalRepository
    ){
        this.doctorRepository = doctorRepository;
        this.doctorHospitalRepository = doctorHospitalRepository;
        this.hospitalRepository = hospitalRepository;
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
    public DoctorResponseDto createDoctor(DoctorRequestDto doctor) throws Exception {
        try {
            Doctor newDoctor =new Doctor();

            String name = InputValidatorUtil.validateStringProperty(MessagesAndContent.DOCTOR_01, doctor.getName(), "Doctor Name", 100);
            newDoctor.setName(name);

            List<Hospital> hospitalList =new ArrayList<>();

            Doctor savedDoctor = doctorRepository.save(newDoctor);
            if (doctor.getHospitals() != null) {
                for (Hospital hospital : doctor.getHospitals()) {
                    Optional<Hospital> foundHospital= hospitalRepository.findById(hospital.getHospitalId());
                    if (foundHospital.isEmpty()) {
                        throw new Exception(MessagesAndContent.HOSPITAL_01);
                    }
                    DoctorHospital newDoctorHospital = new DoctorHospital();
                    newDoctorHospital.setDoctorId(savedDoctor.getId());
                    newDoctorHospital.setHospital(hospital);
                    doctorHospitalRepository.save(newDoctorHospital);
                    hospitalList.add(hospital);
                }
            }
            DoctorResponseDto doctorResponseDto = convertDoctorToDoctorResponseDto(newDoctor);
            doctorResponseDto.setHospitals(hospitalList);

            return doctorResponseDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public DoctorResponseDto updateDoctor(DoctorRequestDto doctor) throws Exception {
        try {
            if(doctor == null) {
                throw new Exception("Invalid input data");
            }

            Optional<Doctor> foundDoctor = doctorRepository.findById(doctor.getId());

            if (foundDoctor.isEmpty()) {
                throw new Exception("Doctor with the ID" + doctor.getId() + "does not exist");
            }

            Doctor currentDoctor = foundDoctor.get();

            String name = InputValidatorUtil.validateStringProperty(MessagesAndContent.DOCTOR_01, doctor.getName(), "Doctor name", 100);

            currentDoctor.setName(name);

            List<Hospital> hospitals =new ArrayList<>();

            if (doctor.getHospitals().size() != 0) {
                List<DoctorHospital> doctorHospitalList = doctorHospitalRepository.findAllByDoctorId(doctor.getId());
                doctorHospitalRepository.deleteAll(doctorHospitalList);


                for (Hospital hospital : doctor.getHospitals()) {
                    Optional<Hospital> foundHospital = hospitalRepository.findById(hospital.getHospitalId());
                    if (foundHospital.isEmpty()) {
                        throw new Exception(MessagesAndContent.HOSPITAL_01);
                    }
                    DoctorHospital newDoctorHospital = new DoctorHospital();
                    newDoctorHospital.setDoctorId(doctor.getId());
                    newDoctorHospital.setHospital(hospital);
                    doctorHospitalRepository.save(newDoctorHospital);
                    hospitals.add(hospital);

                }
            }

            Doctor updatedDoctor =doctorRepository.save(currentDoctor);

            DoctorResponseDto doctorDto = convertDoctorToDoctorResponseDto(updatedDoctor);
            doctorDto.setHospitals(hospitals);
            return doctorDto;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteDoctor(Long id) throws Exception {
        try {
            Optional<Doctor> foundDoctors = doctorRepository.findById(id);

            if (foundDoctors.isPresent()) {
                doctorRepository.delete(foundDoctors.get());
            } else {
                throw new Exception(MessagesAndContent.DOCTOR_02);
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

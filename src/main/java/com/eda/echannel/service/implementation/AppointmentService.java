package com.eda.echannel.service.implementation;

import com.eda.echannel.dto.request.AppointmentRequestDto;
import com.eda.echannel.dto.response.AppointmentResponseDto;
import com.eda.echannel.dto.response.SearchResponseDto;
import com.eda.echannel.model.Appointment;
import com.eda.echannel.model.Channel;
import com.eda.echannel.repository.IAppointmentRepository;
import com.eda.echannel.repository.IDoctorRepository;
import com.eda.echannel.repository.IHospitalRepository;
import com.eda.echannel.repository.ISpecializationRepository;
import com.eda.echannel.service.IAppointmentService;
import com.eda.echannel.util.InputValidatorUtil;
import com.eda.echannel.util.MessagesAndContent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService implements IAppointmentService {
    private final IAppointmentRepository appointmentRepository;
    private final ISpecializationRepository specializationRepository;
    private final IHospitalRepository hospitalRepository;
    private final IDoctorRepository doctorRepository;

    @Autowired
    public AppointmentService(
            IAppointmentRepository appointmentRepository,
            IHospitalRepository hospitalRepository,
            ISpecializationRepository specializationRepository,
            IDoctorRepository doctorRepository
    ){
        this.appointmentRepository = appointmentRepository;
        this.hospitalRepository = hospitalRepository;
        this.specializationRepository = specializationRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public AppointmentResponseDto create(AppointmentRequestDto request) throws Exception {
        try {

            Appointment appointmentRequest = new Appointment();

            if (request == null) {
                throw new Exception("Invalid input data.");
            }

            String patientNIC = InputValidatorUtil.validateStringProperty(MessagesAndContent.APPOINTMENT_01, request.getPatientNIC(), "NIC Number", 20);
            appointmentRequest.setPatientNIC(patientNIC);

            String patientEmail = InputValidatorUtil.validateStringProperty(MessagesAndContent.APPOINTMENT_02, request.getPatientEmail(), "Patient Email", 50);
            appointmentRequest.setPatientEmail(patientEmail);

            String appointmentDate = InputValidatorUtil.validateStringProperty(MessagesAndContent.APPOINTMENT_03, request.getAppointmentDate(), "Appointment Date", 50);
            appointmentRequest.setAppointmentDate(appointmentDate);

            Integer appointmentNumber = request.getAppointmentNumber();
            appointmentRequest.setAppointmentNumber(appointmentNumber);

            Appointment savedAppointment = appointmentRepository.save(appointmentRequest);

            AppointmentResponseDto appointmentDto = convertAppointmentToAppointmentResponseDto(savedAppointment);
            return appointmentDto;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public AppointmentResponseDto findAppointmentbyId(Long appointmentId) throws Exception {
        try {
            Optional<Appointment> foundAppointments = appointmentRepository.findById(appointmentId);

            if (foundAppointments.isPresent()) {
                Appointment foundAppointment = foundAppointments.get();
                AppointmentResponseDto appointmentResponseDto = convertAppointmentToAppointmentResponseDto(foundAppointment);
                return appointmentResponseDto;
            } else {
                throw new Exception("Appointment with the ID " +appointmentId+ " not found.");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public AppointmentResponseDto convertAppointmentToAppointmentResponseDto(Appointment appointment) throws Exception {
        AppointmentResponseDto appointmentResponseDto = new AppointmentResponseDto();

        BeanUtils.copyProperties(appointment, appointmentResponseDto);

        Channel channel = appointment.getChannel();
        SearchResponseDto searchResponseDto = new SearchResponseDto();
        BeanUtils.copyProperties(channel, searchResponseDto);

        searchResponseDto.setDoctorName(doctorRepository.getById(channel.getDoctorId()).getName());
        searchResponseDto.setHospitalName(hospitalRepository.getById(channel.getHospitalId()).getHospitalName());
        searchResponseDto.setSpecializationName(specializationRepository.getById(channel.getSpecializationId()).getSpecializationName());

        appointmentResponseDto.setChannel(searchResponseDto);

        return appointmentResponseDto;
    }
}

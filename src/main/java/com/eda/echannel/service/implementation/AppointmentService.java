package com.eda.echannel.service.implementation;

import com.eda.echannel.dto.response.AppointmentResponseDto;
import com.eda.echannel.model.Appointment;
import com.eda.echannel.repository.IAppointmentRepository;
import com.eda.echannel.service.IAppointmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService implements IAppointmentService {
    private final IAppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(
            IAppointmentRepository appointmentRepository
    ){
        this.appointmentRepository = appointmentRepository;
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
        return appointmentResponseDto;
    }
}

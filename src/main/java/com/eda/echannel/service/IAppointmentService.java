package com.eda.echannel.service;

import com.eda.echannel.dto.response.AppointmentResponseDto;
import com.eda.echannel.model.Appointment;


public interface IAppointmentService {

    public AppointmentResponseDto findAppointmentbyId(Long appointmentId) throws Exception;

    public AppointmentResponseDto convertAppointmentToAppointmentResponseDto(Appointment appointment) throws Exception;

}

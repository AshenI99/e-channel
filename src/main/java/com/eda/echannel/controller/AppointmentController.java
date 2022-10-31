package com.eda.echannel.controller;


import com.eda.echannel.dto.request.AppointmentRequestDto;
import com.eda.echannel.dto.response.AppointmentResponseDto;
import com.eda.echannel.service.IAppointmentService;
import com.eda.echannel.service.implementation.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/appointment")
public class AppointmentController {
    private final IAppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResponseDto> getAppointmentbyId(@PathVariable("appointmentId") Long appointmentId) {

        try {
            AppointmentResponseDto appointmentResponseDto = appointmentService.findAppointmentbyId(appointmentId);
            return new ResponseEntity<>(appointmentResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/create")
    public ResponseEntity<AppointmentResponseDto> createUser(@RequestBody AppointmentRequestDto appointment) {

        try {
            AppointmentResponseDto savedAppointment = appointmentService.create(appointment);

            return new ResponseEntity<>(savedAppointment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}

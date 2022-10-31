//package com.eda.echannel.controller;
//
//import com.eda.echannel.dto.response.HospitalResponseDto;
//import com.eda.echannel.model.Hospital;
//import com.eda.echannel.service.IAppointmentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(allowedHeaders = "*", origins = "*")
//@RequestMapping("/appointment")
//public class AppointmentController {
//    private final IAppointmentService appointmentService;
//
//    @Autowired
//    public AppoinmentController(AppoinmentService appoinmentService){
//        this.appoinmentService = appoinmentService;
//    }
//
//    @GetMapping("{appointmentId}")
//    public ResponseEntity<AppointmentResponseDto> getHospitalbyId(@PathVariable("appointmentId") Long hospitalId) {
//
//        try {
//            HospitalResponseDto hospitalResponseDto = hospitalService.findHospitalbyId(hospitalId);
//            return new ResponseEntity<>(hospitalResponseDto, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//}

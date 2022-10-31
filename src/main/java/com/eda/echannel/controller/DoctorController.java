package com.eda.echannel.controller;


import com.eda.echannel.dto.request.DoctorRequestDto;
import com.eda.echannel.dto.response.DoctorResponseDto;
import com.eda.echannel.model.Doctor;
import com.eda.echannel.service.IDoctorService;
import com.eda.echannel.service.implementation.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/doctor")
public class DoctorController {

    private final IDoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @GetMapping("list")
    public ResponseEntity<List<Doctor>> getAllDoctors () {

        try {
            List<Doctor> doctorList = doctorService.getAll();
            return new ResponseEntity<>(doctorList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{doctorId}")
    public ResponseEntity<DoctorResponseDto> getDoctorById(@PathVariable("doctorId") Long doctorId) {

        try {
            DoctorResponseDto doctorResponseDto = doctorService.findDoctorById(doctorId);
            return new ResponseEntity<>(doctorResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<DoctorResponseDto> createDoctor(@RequestBody DoctorRequestDto doctor) {

        try {
            DoctorResponseDto doctorResponseDto = doctorService.createDoctor(doctor);
            return new ResponseEntity<>(doctorResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

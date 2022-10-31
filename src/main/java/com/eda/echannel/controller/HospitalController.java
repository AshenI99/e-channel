package com.eda.echannel.controller;

import com.eda.echannel.dto.response.HospitalResponseDto;
import com.eda.echannel.dto.response.request.HospitalRequestDto;
import com.eda.echannel.model.Hospital;
import com.eda.echannel.service.IHospitalService;
import com.eda.echannel.service.implementation.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/hospital")
public class HospitalController {

    private final IHospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService){
        this.hospitalService = hospitalService;
    }

    @GetMapping("list")
    public ResponseEntity<List<Hospital>> getAllHospitals () {

        try {
            List<Hospital> hospitalList = hospitalService.getAll();
            return new ResponseEntity<>(hospitalList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{hospitalId}")
    public ResponseEntity<HospitalResponseDto> getHospitalbyId(@PathVariable("hospitalId") Long hospitalId) {

        try {
            HospitalResponseDto hospitalResponseDto = hospitalService.findHospitalbyId(hospitalId);
            return new ResponseEntity<>(hospitalResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/create")
    public ResponseEntity<HospitalResponseDto> createHospital(@RequestBody HospitalRequestDto hospital) {

        try {
            HospitalResponseDto savedHospital = hospitalService.create(hospital);

            return new ResponseEntity<>(savedHospital, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value = "/update/{hospitalName}")
    public ResponseEntity<HospitalResponseDto> updateHospital(@PathVariable("hospitalName") Long hospitalId, @RequestBody HospitalRequestDto hospital) {

        try {
            HospitalResponseDto updateHospital = hospitalService.update(hospitalId, hospital);

            return new ResponseEntity<>(updateHospital, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}

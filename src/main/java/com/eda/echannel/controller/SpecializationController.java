package com.eda.echannel.controller;


import com.eda.echannel.dto.response.SpecializationResponseDto;
import com.eda.echannel.model.Specialization;
import com.eda.echannel.service.ISpecializationService;
import com.eda.echannel.service.implementation.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class SpecializationController {
    private final ISpecializationService specializationService;

    @Autowired
    public SpecializationController(SpecializationService specializationService){
        this.specializationService = specializationService;
    }

    @GetMapping("list")
    public ResponseEntity<List<Specialization>> getAllSpecializations () {

        try {
            List<Specialization> specializationList = specializationService.getAll();
            return new ResponseEntity<>(specializationList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{specializationId}")
    public ResponseEntity<SpecializationResponseDto> getSpecializationbyId(@PathVariable("specializationId") Long specializationId) {

        try {
            SpecializationResponseDto specializationResponseDto = specializationService.findSpecializationbyId(specializationId);
            return new ResponseEntity<>(specializationResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

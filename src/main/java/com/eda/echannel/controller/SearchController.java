package com.eda.echannel.controller;


import com.eda.echannel.dto.request.SearchRequestDto;
import com.eda.echannel.dto.response.SearchResponseDto;
import com.eda.echannel.model.Channel;
import com.eda.echannel.model.Doctor;
import com.eda.echannel.service.IHospitalService;
import com.eda.echannel.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/search-channels")
public class SearchController {

    private final ISearchService searchService;

    @Autowired
    public SearchController(
            ISearchService searchService
    ) {
        this.searchService = searchService;
    }

    @PostMapping("")
    public ResponseEntity<List<SearchResponseDto>> searchChannels (@RequestBody SearchRequestDto searchRequestDto) {

        try {
            List<SearchResponseDto> channelList = searchService.getAllChannels(searchRequestDto);
            return new ResponseEntity<>(channelList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

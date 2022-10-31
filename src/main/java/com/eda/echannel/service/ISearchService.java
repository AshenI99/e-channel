package com.eda.echannel.service;

import com.eda.echannel.dto.request.SearchRequestDto;
import com.eda.echannel.dto.response.DoctorResponseDto;
import com.eda.echannel.dto.response.SearchResponseDto;
import com.eda.echannel.model.Channel;
import com.eda.echannel.model.Doctor;
import com.eda.echannel.model.Hospital;

import java.util.List;

public interface ISearchService {

    public List<SearchResponseDto> getAllChannels(SearchRequestDto searchRequestDto) throws Exception;

    public SearchResponseDto convertChannelToSearchResponseDto(Channel channel) throws Exception;

}

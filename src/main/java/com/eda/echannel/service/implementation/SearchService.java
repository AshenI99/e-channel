package com.eda.echannel.service.implementation;


import com.eda.echannel.dto.request.SearchRequestDto;
import com.eda.echannel.dto.response.DoctorResponseDto;
import com.eda.echannel.dto.response.SearchResponseDto;
import com.eda.echannel.model.Channel;
import com.eda.echannel.model.Hospital;
import com.eda.echannel.repository.*;
import com.eda.echannel.service.ISearchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService implements ISearchService {

    private final ISpecializationRepository specializationRepository;
    private final IHospitalRepository hospitalRepository;
    private final IDoctorRepository doctorRepository;
    private final IChannelRepository channelRepository;
    private final CustomQueryRepository customQueryRepository;

    @Autowired
    public SearchService(
            IChannelRepository channelRepository,
            CustomQueryRepository customQueryRepository,
            IHospitalRepository hospitalRepository,
            ISpecializationRepository specializationRepository,
            IDoctorRepository doctorRepository
    ){
        this.channelRepository = channelRepository;
        this.customQueryRepository = customQueryRepository;
        this.hospitalRepository = hospitalRepository;
        this.specializationRepository = specializationRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<SearchResponseDto> getAllChannels(SearchRequestDto searchRequestDto) throws Exception {

        String searchString = "";

        Long doctorId = searchRequestDto.getDoctorId();
        Long hospitalId = searchRequestDto.getHospitalId();
        Long specializationId = searchRequestDto.getSpecializationId();
        String date = searchRequestDto.getDate();

        if(doctorId == null && hospitalId == null && specializationId == null){
            throw new Exception("Required fields are missing");
        }

        if(doctorId != null){
            searchString += "doctor_id=" + doctorId;
        }

        if(hospitalId != null){
            if(!searchString.isEmpty()){
                searchString += " and ";
            }
            searchString += "hospital_id=" + hospitalId;
        }

        if(specializationId != null){
            if(!searchString.isEmpty()){
                searchString += " and ";
            }
            searchString += "specialization_id=" + specializationId;
        }

        if(date != null){
            searchString += " and date='" + date + "'";
        }

        List<Channel> channelList = customQueryRepository.getChannelList(searchString);

        List<SearchResponseDto> responseDtoList = new ArrayList<SearchResponseDto>();

        for (Channel channel : channelList) {
            responseDtoList.add(convertChannelToSearchResponseDto(channel));
        }

        return responseDtoList;
    }

    @Override
    public SearchResponseDto convertChannelToSearchResponseDto(Channel channel) throws Exception {
        SearchResponseDto searchResponseDto = new SearchResponseDto();
        BeanUtils.copyProperties(channel, searchResponseDto);

        searchResponseDto.setDoctorName(doctorRepository.findById(channel.getDoctorId()).get().getName());
        searchResponseDto.setHospitalName(hospitalRepository.findById(channel.getHospitalId()).get().getHospitalName());
        searchResponseDto.setSpecializationName(specializationRepository.findById(channel.getSpecializationId()).get().getSpecializationName());
        return searchResponseDto;
    }
}

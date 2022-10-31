package com.eda.echannel.service.implementation;

import com.eda.echannel.dto.request.ChannelUpdateRequestDto;
import com.eda.echannel.dto.response.DoctorResponseDto;
import com.eda.echannel.dto.response.HospitalResponseDto;
import com.eda.echannel.model.Channel;
import com.eda.echannel.model.Doctor;
import com.eda.echannel.model.Hospital;
import com.eda.echannel.repository.IChannelRepository;
import com.eda.echannel.repository.IDoctorRepository;
import com.eda.echannel.repository.IHospitalRepository;
import com.eda.echannel.repository.ISpecializationRepository;
import com.eda.echannel.service.IChannelService;
import com.eda.echannel.service.IDoctorService;
import com.eda.echannel.util.InputValidatorUtil;
import com.eda.echannel.util.MessagesAndContent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ChannelService implements IChannelService {

    private final ISpecializationRepository specializationRepository;
    private final IHospitalRepository hospitalRepository;
    private final IDoctorRepository doctorRepository;
    private final IChannelRepository channelRepository;

    @Autowired
    public ChannelService(
            IChannelRepository channelRepository,
            IHospitalRepository hospitalRepository,
            ISpecializationRepository specializationRepository,
            IDoctorRepository doctorRepository
    ){
        this.channelRepository = channelRepository;
        this.hospitalRepository = hospitalRepository;
        this.specializationRepository = specializationRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Channel createChannel(Channel channel) throws Exception {
        try {

            Channel savingChannel = new Channel();

            if (channel == null) {
                throw new Exception("Invalid input data.");
            }

            Long doctorId = channel.getDoctorId();
            if(!doctorRepository.existsById(doctorId)){
                throw new Exception("Doctor with the ID " +doctorId+ " not found");
            }
            savingChannel.setDoctorId(doctorId);

            Long hospitalId = channel.getHospitalId();
            if(!hospitalRepository.existsById(hospitalId)){
                throw new Exception("Hospital with the ID " +hospitalId+ " not found");
            }
            savingChannel.setHospitalId(hospitalId);

            Long specializationId = channel.getSpecializationId();
            if(!specializationRepository.existsById(specializationId)){
                throw new Exception("Specialization with the ID " +specializationId+ " not found");
            }
            savingChannel.setSpecializationId(specializationId);

            String dateTime = channel.getDateTime();
            DateTimeFormatter DTF = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");

            try {
                LocalDateTime LDT = LocalDateTime.parse(dateTime, DTF);
                savingChannel.setDateTime(dateTime);
            } catch (Exception e){
                throw new Exception("Provide date and time in YYYY-MM-DD HH:mm format");
            }


            Double fee = channel.getFee();
            if(fee == null){
                throw new Exception("Channel fee is required");
            }
            savingChannel.setFee(fee);

            Integer maximumPatients = channel.getMaximumPatients();
            if(maximumPatients == null){
                throw new Exception("Maximum patient count is required");
            }
            savingChannel.setMaximumPatients(maximumPatients);

            savingChannel.setActivePatients(0);

            Channel savedChannel = channelRepository.save(savingChannel);

            return savedChannel;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Channel updateChannel(Long channelId, ChannelUpdateRequestDto channelUpdateRequestDto) throws Exception {
        try {

            if (channelUpdateRequestDto == null) {
                throw new Exception("Invalid input data.");
            }

            if(!channelRepository.existsById(channelId)){
                throw new Exception("Channel with the ID " +channelId+ " not found");
            }

            Channel oldChannel = channelRepository.findById(channelId).get();

            Integer maximumPatients = channelUpdateRequestDto.getMaximumPatients();

            if(maximumPatients != null){
                Integer activePatients = oldChannel.getActivePatients();

                if(activePatients > maximumPatients){
                    throw new Exception("Limit is already reached");
                }
                oldChannel.setMaximumPatients(maximumPatients);
            }


            String dateTime = channelUpdateRequestDto.getDateTime();

            if(dateTime != null){
                String oldDateTime = oldChannel.getDateTime();
                DateTimeFormatter DTF = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");

                try {
                    LocalDateTime OLDT = LocalDateTime.parse(oldDateTime, DTF);
                    LocalDateTime LDT = LocalDateTime.parse(dateTime, DTF);

                    int OY = OLDT.getYear();
                    int Y = LDT.getYear();

                    if(Y < OY){
                        throw new Exception("Cannot assign past date");
                    }

                    int OM = OLDT.getMonthValue();
                    int M = LDT.getMonthValue();

                    if(M < OM){
                        throw new Exception("Cannot assign past date");
                    }

                    int OD = OLDT.getDayOfMonth();
                    int D = LDT.getDayOfMonth();

                    if(D < OD){
                        throw new Exception("Cannot assign past date");
                    }

                    int OH = OLDT.getHour();
                    int H = LDT.getHour();

                    if(H < OH){
                        throw new Exception("Cannot assign past time");
                    }

                    int OMin = OLDT.getMinute();
                    int Min = LDT.getMinute();

                    if(Min < OMin){
                        throw new Exception("Cannot assign past time");
                    }

                    oldChannel.setDateTime(dateTime);

                } catch (Exception e){
                    throw new Exception("Provide date and time in YYYY-MM-DD HH:mm format");
                }
            }

            Channel savedChannel = channelRepository.save(oldChannel);

            return savedChannel;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Channel deleteChannel(Long channelId) throws Exception {

        if(!channelRepository.existsById(channelId)){
            throw new Exception("Channel with the ID " +channelId+ " not found");
        }

        Channel oldChannel = channelRepository.findById(channelId).get();
        channelRepository.deleteById(channelId);

        return oldChannel;
    }

    @Override
    public Channel getChannelById(Long channelId) throws Exception {
        try {
            Optional<Channel> foundChannels = channelRepository.findById(channelId);

            if (foundChannels.isPresent()) {
                return foundChannels.get();
            } else {
                throw new Exception("Channel with the ID " +channelId+ " not found");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

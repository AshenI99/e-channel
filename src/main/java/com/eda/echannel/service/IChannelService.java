package com.eda.echannel.service;

import com.eda.echannel.dto.request.ChannelUpdateRequestDto;
import com.eda.echannel.dto.response.SearchResponseDto;
import com.eda.echannel.model.Channel;

import java.util.List;

public interface IChannelService {

    public Channel createChannel(Channel channel) throws Exception;

    public Channel updateChannel(Long channelId, ChannelUpdateRequestDto channelUpdateRequestDto) throws Exception;

    public Channel deleteChannel(Long channelId) throws Exception;

    public Channel getChannelById(Long channelId) throws Exception;

    List<SearchResponseDto> getAllChannels() throws Exception;

    SearchResponseDto convertChannelToSearchResponseDto(Channel channel) throws Exception;
}

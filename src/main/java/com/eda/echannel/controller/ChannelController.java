package com.eda.echannel.controller;

import com.eda.echannel.dto.request.ChannelUpdateRequestDto;
import com.eda.echannel.model.Channel;
import com.eda.echannel.service.IChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/channel")
public class ChannelController {

    private final IChannelService channelService;

    @Autowired
    public ChannelController(IChannelService channelService){
        this.channelService = channelService;
    }

    @GetMapping("{channelId}")
    public ResponseEntity<Channel> getChannelById(@PathVariable("channelId") Long channelId) {

        try {
            Channel channel = channelService.getChannelById(channelId);
            return new ResponseEntity<>(channel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Channel> createChannel(@RequestBody Channel channel) {

        try {
            Channel savedChannel = channelService.createChannel(channel);
            return new ResponseEntity<>(savedChannel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{channelId}")
    public ResponseEntity<Channel> updateChannel(@PathVariable("channelId") Long channelId, @RequestBody ChannelUpdateRequestDto channelUpdateRequestDto) {

        try {
            Channel savedChannel = channelService.updateChannel(channelId, channelUpdateRequestDto);
            return new ResponseEntity<>(savedChannel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{channelId}")
    public ResponseEntity<Channel> updateChannel(@PathVariable("channelId") Long channelId) {

        try {
            Channel savedChannel = channelService.deleteChannel(channelId);
            return new ResponseEntity<>(savedChannel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

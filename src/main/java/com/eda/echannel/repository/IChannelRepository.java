package com.eda.echannel.repository;

import com.eda.echannel.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IChannelRepository extends JpaRepository <Channel, Long> {

}

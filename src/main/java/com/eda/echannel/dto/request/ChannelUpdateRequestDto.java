package com.eda.echannel.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelUpdateRequestDto {
    private String dateTime;
    private Integer maximumPatients;
}

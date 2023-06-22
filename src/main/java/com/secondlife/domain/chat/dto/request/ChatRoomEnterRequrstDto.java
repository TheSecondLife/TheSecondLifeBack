package com.secondlife.domain.chat.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@Data
@ToString
public class ChatRoomEnterRequrstDto {

    Long userA;
    Long UserB;

}

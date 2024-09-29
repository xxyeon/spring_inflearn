package com.example.spring_practice;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum MessageType {

    LOVE("사랑합니다.", SendMediaType.SMS),
    THANKS("감사합니다.", SendMediaType.EMAIL);

    @Getter
    String message;
    @Getter
    SendMediaType sendMediaType;

    public static MessageType findByName(String type) {
        return MessageType.valueOf(type);
    }

}

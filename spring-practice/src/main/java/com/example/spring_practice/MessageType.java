package com.example.spring_practice;

import lombok.Getter;

public enum MessageType {

    LOVE("사랑합니다.", SendMediaType.SMS),
    THANKS("감사합니다.", SendMediaType.EMAIL);

    @Getter
    private final String message;
    @Getter
    private final SendMediaType sendMediaType;

    MessageType(String message, SendMediaType sendMediaType) {
        this.message = message;
        this.sendMediaType = sendMediaType;
    }

    public static MessageType findByName(String type) {
        return MessageType.valueOf(type);
    }

}

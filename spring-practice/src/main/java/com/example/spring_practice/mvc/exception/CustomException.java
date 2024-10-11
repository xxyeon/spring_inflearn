package com.example.spring_practice.mvc.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    ExceptionType type;

    public CustomException(ExceptionType type){
        super();
    }

    public CustomException(ExceptionType type, Object message){
        super(type.getDesc() + message.toString());
        this.type = type;
    }
}

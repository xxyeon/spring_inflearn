package com.example.spring_practice.mvc.controller.advice;


import com.example.spring_practice.mvc.controller.dot.common.BaseResponse;
import com.example.spring_practice.mvc.exception.CustomException;
import com.example.spring_practice.mvc.exception.ExceptionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler
    public BaseResponse<Void> handle(CustomException e) {
        ExceptionType type = e.getType();
        log.atLevel(type.getLevel()).setCause(e).log(e.getMessage());
        return BaseResponse.failure(type);
    }

    @ExceptionHandler
    public BaseResponse<List<FieldErrorDto>> handle(MethodArgumentNotValidException e) {
        List<FieldErrorDto> errors = new ArrayList<>();
        StringBuilder messageBuilder = new StringBuilder();
        for (ObjectError each : e.getBindingResult().getAllErrors()) {
            FieldError eachError = (FieldError) each;
            messageBuilder.append(String.format("[%s =%s : %s]", eachError.getField(), eachError.getRejectedValue(), eachError.getDefaultMessage()));
            errors.add(new FieldErrorDto(eachError.getField(), eachError.getRejectedValue(), eachError.getDefaultMessage()));
        }
        log.warn(messageBuilder.toString(), e);
        return BaseResponse.failure(ExceptionType.INVALID_INPUT, errors);
    }

    @ExceptionHandler
    public BaseResponse<Void> handle(Exception e) {
        log.error(e.getMessage(), e);
        return BaseResponse.failure(ExceptionType.UNCLASSIFIED_ERROR);
    }
}
